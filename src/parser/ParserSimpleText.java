package parser;

import dom.DOMException;
import dom_public.NodePublic;
import dom_public.TextPublic;
import element_dom.A;
import element_dom.Br;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserSimpleText extends Parser {

    private final List<NodePublic> listSimpleText;

    public ParserSimpleText(ReviewTokenizer reviewTok) {
        super(reviewTok);
        listSimpleText = new ArrayList<>();
    }

    @Override
    public List<NodePublic> parse() {
        parseSimpleText();
        return listSimpleText;
    }

    private void parseSimpleText() {
        boolean callBack = false;
        try {
            A a = parseA();
            if (a != null) {
                listSimpleText.add(a);
                callBack = true;
            }
            Br br = parseBr();
            if (br != null){
                listSimpleText.add(br);
                callBack = true;
            }
        } catch (DOMException domException) {           
            throw domException;
        }
        TextPublic text = reviewTok.getText();
        if (text != null) {
            listSimpleText.add(text);
            callBack = true;
        }
        if (callBack) {
            parseSimpleText();
        }
    }

    private A parseA() {
        A a = null;
        boolean isA = reviewTok.isExpectedTag("a", false);
        if (isA) {
            a = new A();
            a = (A) addAttrToElement(a);
            TextPublic text = reviewTok.getText();
            a.appendChild(text);
            try {
                verifyCloseOfTag("a");
            } catch (DOMException domException) {
                a = null;
                throw domException;
            }
        }
        return a;
    }

    private Br parseBr() {
        Br br = null;
        boolean isBr = reviewTok.isExpectedTag("br", false);
        if (isBr) {
            br = new Br();
        }
        return br;
    }

}
