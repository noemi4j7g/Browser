package parser;

import dom.DOMException;
import element_dom.Body;
import element_dom.Head;
import element_dom.Html;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserHtml extends Parser {

    private Html html;

    public ParserHtml(ReviewTokenizer reviewTok) {
        super(reviewTok);
        html = null;
    }

    @Override
    public Html parse() {
        boolean isCloseTag = false;
        boolean isTagHtml = reviewTok.isExpectedTag("html", isCloseTag);
        if (isTagHtml) {
            html = new Html();
            html = (Html) addAttrToElement(html);
            try {
                parseContentHtml();
                verifyCloseOfTag("html");
            } catch (DOMException domException) {
                html = null;
                throw domException;
            }
        } else {
            throw new DOMException((short) 12, "class ParserHtml. Error of syntax, expected \"<html>\"");
        }
        return html;
    }

    private void parseContentHtml() {
        ParserHead parserHead = new ParserHead(reviewTok);
        try {
            Head head = parserHead.parse();
            html.appendChild(head);
            ParserBody parserBody = new ParserBody(reviewTok);
            Body body = parserBody.parse();
            html.appendChild(body);
        } catch (DOMException domException) {
            throw domException;
        }
    }

}
