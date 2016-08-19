package parser;

import dom.DOMException;
import dom_public.ElementPublic;
import dom_public.NodePublic;
import element_dom.P;
import element_dom.Div;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserNestedText extends Parser {

    private final List<NodePublic> listNestedText;

    public ParserNestedText(ReviewTokenizer reviewTok) {
        super(reviewTok);
        listNestedText = new ArrayList<>();
    }

    @Override
    public List<NodePublic> parse() {
        parseNestedText();
        return listNestedText;
    }

    private void parseNestedText() {
        boolean callBack = false;
        try{
            P p = (P) parse("p");
            if (p != null) {
                listNestedText.add(p);
                callBack = true;
            }
            Div div = (Div) parse("div");
            if(div != null){
                listNestedText.add(div);
                callBack = true;
            }
            ParserList parserList = new ParserList(reviewTok);
            List<NodePublic> listElementList = parserList.parse();
            if(!listElementList.isEmpty()){
                listNestedText.addAll(listElementList);
                callBack = true;
            }
            if(callBack){
                parseNestedText();
            }
        }catch(DOMException domException){           
            throw domException;
        }
    }

    private NodePublic parse(String nameTag) {
        ElementPublic elemNestedText = null;
        boolean isCloseTag = false;
        boolean isExpectedTag = reviewTok.isExpectedTag(nameTag, isCloseTag);
        if (isExpectedTag) {
            switch(nameTag){
                case "p":
                    elemNestedText = new P();
                    elemNestedText = addAttrToElement(elemNestedText);
                    break;
                case "div":
                    elemNestedText = new Div();
                    elemNestedText = addAttrToElement(elemNestedText);
                    break;
            }
            ParserElementText parserElementText = new ParserElementText(reviewTok);
            List<NodePublic> listElementText = parserElementText.parse();
            elemNestedText = editElement(elemNestedText, listElementText);
            try{
                verifyCloseOfTag(nameTag);
            }catch(DOMException domException){
                elemNestedText = null;
                throw domException;
            }
        }
        return elemNestedText;
    }
}
