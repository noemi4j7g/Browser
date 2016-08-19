package parser;

import dom.DOMException;
import element_dom.Head;
import element_dom.Link;
import element_dom.Script;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserHeadTest {
    
    @Test (expected = ExceptionInInitializerError.class)
    public void testParseHeadWithNullData() {
        ReviewTokenizer reviewTok = null;
        ParserHead parserHead = new ParserHead(reviewTok);
    }
    
    @Test
    public void testParseHeadWithValidData() {
        String text = "<head></head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);
        Head expectedHead = new Head();
        Head actualHead = parserHead.parse();
        assertEquals(expectedHead, actualHead);
    }
    
    @Test (expected = DOMException.class)
    public void testParseHeadWithInvalidData() {
        String text = "<head></body>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);
        parserHead.parse();
    }
    
    @Test
    public void testParseHeadWithInvalidAttribute(){
        String nameAttrName = "name";
        String valueAttrName = "\"nameHead\"";
        String nameAttrId = "id";
        String valueAttrId = "\"idHead\"";
        String text = String.format("<head %s=%s %s=%s></head>", nameAttrName, valueAttrName, nameAttrId, valueAttrId);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parser = new ParserHead(reviewTok);
        Head expectedHead = new Head();
        Head actualHead = parser.parse();
        assertEquals(expectedHead, actualHead);
    }
    
    @Test (expected = DOMException.class)
    public void testParseHeadWithoutCloseTag() {
        String text = "<head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);
        parserHead.parse();
    }
    
    @Test (expected = DOMException.class)
    public void testParseHeadWithInvalidContent() {
        String text = "<head><body></body></head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);
        parserHead.parse();
    }
    
    @Test
    public void testParseHeadWithValidContent() {
        String text = "<head><link/><script></script></head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);
        Head expectedHead = new Head();
        expectedHead.appendChild(new Link());
        expectedHead.appendChild(new Script());
        Head actualHead = parserHead.parse();
        assertEquals(expectedHead, actualHead);
    }
    
    @Test
    public void testParseRecursiveContentHead() {
        String text = "<head><link/><script></script><script></script><link/><link/></head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserHead parserHead = new ParserHead(reviewTok);     
        Head expectedHead = new Head();
        expectedHead.appendChild(new Link());
        expectedHead.appendChild(new Script());
        expectedHead.appendChild(new Script());
        expectedHead.appendChild(new Link());
        expectedHead.appendChild(new Link());
        Head actualHead = parserHead.parse();
        assertEquals(expectedHead, actualHead);
    }
    
}
