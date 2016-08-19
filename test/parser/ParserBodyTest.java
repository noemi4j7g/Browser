package parser;

import dom.DOMException;
import dom_public.ElementPublic;
import dom_public.TextPublic;
import element_dom.Body;
import element_dom.H1;
import element_dom.H2;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserBodyTest {
    
    @Test (expected = ExceptionInInitializerError.class)
    public void testParseBodyWithNullData() {
        ReviewTokenizer reviewTok = null;
        ParserBody parserBody = new ParserBody(reviewTok);
    }
    
    @Test
    public void testParseBodyWithValidData() {
        String text = "<body></body>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        Body expectedBody = new Body();
        Body actualBody = parserBody.parse();
        assertEquals(expectedBody, actualBody);
    }
    
    @Test (expected = DOMException.class)
    public void testParseBodyWithInvalidData() {
        String text = "<body></head>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        parserBody.parse();
    }
    
    @Test (expected = DOMException.class)
    public void testParseBodyWithoutCloseTag() {
        String text = "<body>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        parserBody.parse();
    }
    
    @Test
    public void testParseBodyWithAttribute(){
        String nameAttrName = "class";
        String valueAttrName = "\"classBody\"";
        String nameAttrId = "id";
        String valueAttrId = "\"idBody\"";
        String text = String.format("<body %s=%s %s=%s></body>", nameAttrName, valueAttrName, nameAttrId, valueAttrId);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        Body expectedBody = new Body();
        expectedBody.setAttribute(nameAttrName, valueAttrName);
        expectedBody.setAttribute(nameAttrId, valueAttrId);
        Body actualBody = parserBody.parse();
        assertEquals(expectedBody, actualBody);
    }
    
    @Test (expected = DOMException.class)
    public void testParseBodyWithInvalidContent() {
        String text = "<body><head></head></body>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        parserBody.parse();
    }
    
    @Test
    public void testParseBodyWithTitleWithAttribute() {
        String nameAttrIdH1 = "id";
        String valueAttrIdH1 = "\"titleH1\"";
        String nameAttrIdH2 = "id";
        String valueAttrIdH2 = "\"titleH2\"";
        String text = String.format("<body><h1 %s=%s></h1><h2 %s=%s></h2></body>", nameAttrIdH1, valueAttrIdH1, nameAttrIdH2, valueAttrIdH2);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserBody = new ParserBody(reviewTok);
        
        Body expectedBody = new Body();
        
        H1 h1 = new H1();
        h1.setAttribute(nameAttrIdH1, valueAttrIdH1);
        expectedBody.appendChild(h1);
        
        H2 h2 = new H2();
        h2.setAttribute(nameAttrIdH2, valueAttrIdH2);
        expectedBody.appendChild(h2);
        
        Body actualBody = parserBody.parse();
        assertEquals(expectedBody, actualBody);
    }
    
    @Test
    public void testParseRecursiveContentBody() {
        String firstContTextH1 = "Title 1";
        String secondContTextH1 = "Title 1.1";
        String contentTextH2 = "Title 2";
        String contentTextBody = "Text of body";
        String text = String.format("<body><h1>%s</h1>%s<h1>%s</h1><h2>%s</h2></body>", firstContTextH1, contentTextBody, secondContTextH1, contentTextH2);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserBody parserbody = new ParserBody(reviewTok);
        
        ElementPublic expectedElement = new Body();
        
        ElementPublic firstH1 = new H1();
        TextPublic firstTextH1 = new TextPublic(firstContTextH1);
        firstH1.appendChild(firstTextH1);
        expectedElement.appendChild(firstH1);
        
        TextPublic textBody = new TextPublic(contentTextBody);
        expectedElement.appendChild(textBody);
        
        H1 secondH1 = new H1();
        TextPublic secondTextH1 = new TextPublic(secondContTextH1);
        secondH1.appendChild(secondTextH1);
        expectedElement.appendChild(secondH1);
        
        H2 h2 = new H2();
        TextPublic textH2 = new TextPublic(contentTextH2);
        h2.appendChild(textH2);
        expectedElement.appendChild(h2);
        
        ElementPublic actualElement = parserbody.parse();
        assertEquals(expectedElement, actualElement);
    }
    
}
