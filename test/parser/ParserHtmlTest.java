package parser;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import dom.DOMException;
import element_dom.Body;
import element_dom.Head;
import element_dom.Html;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserHtmlTest {
    
    @Test (expected = ExceptionInInitializerError.class)
    public void testClientWithNullData() {
        ReviewTokenizer reviewTok = null;
        ParserHtml parserHtml = new ParserHtml(reviewTok);
    }
    
    @Test
    public void testParseHtmlWithValidData() {
        String text = "<html></html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        Html expectedHtml = new Html();
        Html actualHtml = parserHtml.parse();
        assertEquals(expectedHtml, actualHtml);
    }
    
    @Test (expected = DOMException.class)
    public void testParseHtmlWithInvalidData() {
        String text = "<head></head>";
        Tokenizer tokenizerOne = new Tokenizer(text);
        ReviewTokenizer reviewTokOne = new ReviewTokenizer(tokenizerOne);
        ParserHtml parserHtml = new ParserHtml(reviewTokOne);
        Html html = parserHtml.parse();
    }
    
    @Test
    public void testParseHtmlWithAttribute(){
        String nameAttr = "lang";
        String valueAttr = "\"es\"";
        String textDoc = String.format("<html %s=%s></html>", nameAttr, valueAttr);
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parser = new ParserHtml(reviewTok);
        Html expectedHtml = new Html();
        expectedHtml.setAttribute(nameAttr, valueAttr);
        Html actualHtml = parser.parse();
        assertEquals(expectedHtml, actualHtml);
    }
    
    @Test (expected = DOMException.class)
    public void testParseHtmlWithoutCloseTag() {
        String text = "<html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        parserHtml.parse();
    }
    
    @Test
    public void testParseHtmlWithHead() {
        String text = "<html><head></head></html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        Html expectedHtml = new Html();
        expectedHtml.appendChild(new Head());
        Html actualHtml = parserHtml.parse();
        assertEquals(expectedHtml, actualHtml);
    }
    
    @Test
    public void testParseHtmlWithBody() {
        String text = "<html><body></body></html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        Html expectedHtml = new Html();
        expectedHtml.appendChild(new Body());
        Html actualHtml = parserHtml.parse();
        assertEquals(expectedHtml, actualHtml);
    }
    
    @Test
    public void testParseHtmlWithValidContent() {
        String text = "<html><head></head><body></body></html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        Html expectedHtml = new Html();
        expectedHtml.appendChild(new Head());
        expectedHtml.appendChild(new Body());
        Html actualHtml = parserHtml.parse();
        assertEquals(expectedHtml, actualHtml);
    }
    
    @Test (expected = DOMException.class)
    public void testParseHtmlWithOtherElement() {
        String text = "<html><script></script></html>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHtml parserHtml = new ParserHtml(reviewTok);
        parserHtml.parse();
    }
}
