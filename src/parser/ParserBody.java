package parser;

import dom.DOMException;
import dom_public.ElementPublic;
import dom_public.TextPublic;
import element_dom.Body;
import dom_public.NodePublic;
import element_dom.H1;
import element_dom.H2;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserBody extends Parser {

    private Body body;

    public ParserBody(ReviewTokenizer reviewTok) {
        super(reviewTok);
        body = null;
    }

    @Override
    public Body parse() {
        boolean isBody = reviewTok.isExpectedTag("body", false);
        if (isBody) {
            body = new Body();
            body = (Body) addAttrToElement(body);
            try {
                parseContentBody();
                verifyCloseOfTag("body");
            } catch (DOMException domException) {
                body = null;
                throw domException;
            }
        }
        return body;
    }

    private void parseContentBody() {
        boolean callBack = false;
        try {
            ElementPublic h1 = parseTitle("h1");
            if (h1 != null) {
                body.appendChild(h1);
                callBack = true;
            }
            ElementPublic h2 = parseTitle("h2");
            if (h2 != null) {
                body.appendChild(h2);
                callBack = true;
            }
            List<NodePublic> listElementText = getListElementText();
            boolean availableList = listElementText != null && !listElementText.isEmpty();
            if (availableList) {
                body = (Body) editElement(body, listElementText);
                callBack = true;
            }
            if (callBack) {
                parseContentBody();
            }
        } catch (DOMException domException) {
            throw domException;
        }
    }

    private List<NodePublic> getListElementText() {
        ParserElementText parserElementText = new ParserElementText(reviewTok);
        List<NodePublic> listElementText = parserElementText.parse();
        return listElementText;
    }

    private ElementPublic parseTitle(String nameTag) {
        boolean isCloseTag = false;
        boolean isExpectedTag = reviewTok.isExpectedTag(nameTag, isCloseTag);
        ElementPublic element = null;
       
        if (isExpectedTag) {
            switch (nameTag) {
                case "h1":
                    element = new H1();
                    element = (H1) this.addAttrToElement(element);
                    break;
                case "h2":
                    element = new H2();
                    element = (H2) this.addAttrToElement(element);
                    break;
            }
            if (element != null) {
                TextPublic textDom = reviewTok.getText();                
                textDom.setNameTag(nameTag);
                element.appendChild(textDom);
                try {
                    verifyCloseOfTag(nameTag);
                } catch (DOMException domException) {
                    throw domException;
                }
            }
        }
        return element;
    }

}
