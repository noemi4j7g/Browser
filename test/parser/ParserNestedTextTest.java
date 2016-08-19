package parser;

import dom.DOMException;
import dom_public.NodePublic;
import dom_public.TextPublic;
import element_dom.A;
import element_dom.P;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserNestedTextTest {
    
     @Test (expected = ExceptionInInitializerError.class)
    public void testParseNestedTextWithNullData() {
        ReviewTokenizer reviewTok = null;
        ParserNestedText parserNestedText = new ParserNestedText(reviewTok);
    }
    
    @Test
    public void testParsePWithAttribute() {
        String nameAttr = "name";
        String valueAttr = "\"paragraph\"";
        String text = String.format("<p %s=%s></p>", nameAttr, valueAttr);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserNestedText parserNestedText = new ParserNestedText(reviewTok);
        P p = new P();
        p.setAttribute(nameAttr, valueAttr);
        List<NodePublic> expectedList = Arrays.asList(p);
        List<NodePublic> actualList = parserNestedText.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testParsePWithElementTexts() {
        String firstTextP = "This is the text of P";
        String secondTextP = "Text after the tag 'a'";
        String firstTextA = "Link 1";
        String thirdTextA = "Reference 2";
        String text = String.format("<p>%s<a>%s</a>%s<a></a><a>%s</a></p>", firstTextP, firstTextA, secondTextP, thirdTextA);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserNestedText parserNestedText = new ParserNestedText(reviewTok);     
        P p = new P();
        
        TextPublic firstTextDomP = new TextPublic(firstTextP);
        p.appendChild(firstTextDomP);
        
        A firstA = new A();
        TextPublic firstTextDomA = new TextPublic(firstTextA);
        firstA.appendChild(firstTextDomA);
        p.appendChild(firstA);
        
        TextPublic secondTextDomP = new TextPublic(secondTextP);
        p.appendChild(secondTextDomP);
        
        p.appendChild(new A());
        
        A thirdA = new A();
        TextPublic thirdTextDomA = new TextPublic(thirdTextA);
        thirdA.appendChild(thirdTextDomA);
        p.appendChild(thirdA);

        List<NodePublic> expectedList = Arrays.asList(p);
        List<NodePublic> actualList = parserNestedText.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test (expected = DOMException.class)
    public void testParseNestedTextWithInvalidData() {
        String text = "<p><script></script></p>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserNestedText parserNestedText = new ParserNestedText(reviewTok);
        parserNestedText.parse();
    }
    
}
