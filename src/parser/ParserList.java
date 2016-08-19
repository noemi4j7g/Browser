package parser;

import dom.DOMException;
import dom_public.NodePublic;
import element_dom.Li;
import element_dom.Ul;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserList extends Parser {

    private final List<NodePublic> listListDom;

    public ParserList(ReviewTokenizer reviewTok) {
        super(reviewTok);
        listListDom = new ArrayList<>();
    }

    @Override
    public List<NodePublic> parse() {
        parseListDom();
        return listListDom;
    }

    private void parseListDom() {
        try {
            Ul ul = parseUl();
            boolean callBack = ul != null;
            if (callBack) {
                listListDom.add(ul);
                parseListDom();
            }
        } catch (DOMException domException) {       
            throw domException;
        }
    }

    public Ul parseUl() {
        Ul ul = null;
        boolean isCloseTag = false;
        boolean isExpectedTag = reviewTok.isExpectedTag("ul", isCloseTag);
        if (isExpectedTag) {
            ul = new Ul();
            ul = (Ul) addAttrToElement(ul);
            ul = parseElementList(ul);
            try {
                verifyCloseOfTag("ul");
            } catch (DOMException dOMException) {
                ul = null;
                throw dOMException;
            }
        }
        return ul;
    }

    private Ul parseElementList(Ul ul) {
        Ul editedUl = (Ul) ul.cloneNode(true);
        boolean isCloseTag = false;
        boolean isExpectedTag = reviewTok.isExpectedTag("li", isCloseTag);
        boolean callBack = false;
        if (isExpectedTag) {
            Li li = new Li();
            li = (Li) addAttrToElement(li);
            ParserElementText parserElementText = new ParserElementText(reviewTok);
            List<NodePublic> listElementText = parserElementText.parse();
            if(!listElementText.isEmpty()){
                li = (Li) editElement(li, listElementText);
                callBack = true;
            }
            Ul ulLi = parseUl();
            if(ulLi != null){
                li.appendChild(ulLi);
                callBack = true;
            }
            try{
                verifyCloseOfTag("li");
                editedUl.appendChild(li);
                if(callBack){
                    editedUl = parseElementList(editedUl);
                }
            }catch(DOMException domException){
                throw domException;
            }
        }
        return editedUl;
    }

}
