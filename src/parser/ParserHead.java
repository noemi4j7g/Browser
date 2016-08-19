package parser;

import dom.DOMException;
import element_dom.Head;
import element_dom.Link;
import element_dom.Script;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserHead extends Parser {

    private Head head;

    public ParserHead(ReviewTokenizer reviewTok) {
        super(reviewTok);
        head = null;
    }

    @Override
    public Head parse() {
        boolean isCloseTag = false;
        boolean isTagHead = reviewTok.isExpectedTag("head", isCloseTag);
        if (isTagHead) {
            head = new Head();
            head = (Head) addAttrToElement(head);
            try {
                parseContentHead();
                verifyCloseOfTag("head");
            } catch (DOMException domException) {
                head = null;
                throw domException;
            }
        }
        return head;
    }

    private void parseContentHead() {
        boolean callBack = false;
        Link link = parseLink();
        boolean linkNull = link == null;
        if (!linkNull) {
            head.appendChild(link);
            callBack = true;
        }
        try {
            Script script = parseScript();
            boolean scriptNull = script == null;
            if (!scriptNull) {
                head.appendChild(script);
                callBack = true;
            }
            if (callBack) {
                parseContentHead();
            }
        } catch (DOMException domException) {
            throw domException;
        }
    }

    private Link parseLink() {
        Link link = null;
        boolean isCloseTag = false;
        boolean isLink = reviewTok.isExpectedTag("link", isCloseTag);
        if (isLink) {
            link = new Link();
            link = (Link) addAttrToElement(link);
        }
        return link;
    }

    private Script parseScript() {
        Script script = null;
        boolean isCloseTag = false;
        if (reviewTok.isExpectedTag("script", isCloseTag)) {
            script = new Script();
            script = (Script) addAttrToElement(script);
            try{
                verifyCloseOfTag("script");
            }catch(DOMException dOMException){
                script = null;
                throw dOMException;
            }
        }
        return script;
    }

}
