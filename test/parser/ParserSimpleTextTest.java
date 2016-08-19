package parser;

import dom_public.NodePublic;
import dom_public.TextPublic;
import element_dom.A;
import element_dom.Br;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserSimpleTextTest {
    
    @Test (expected = ExceptionInInitializerError.class)
    public void testParseSimpleTextWithNullData() {
        ReviewTokenizer reviewTok = null;
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);
    }
    
    @Test
    public void testParseA() {
        String nameAttr = "href";
        String valueAttr = "\"refA\"";
        String textA = "This is the link of A";
        String text = String.format("<a %s=%s>%s</a>", nameAttr, valueAttr, textA);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);     
        A a = new A();
        a.setAttribute(nameAttr, valueAttr);
        TextPublic textOfA = new TextPublic(textA);
        a.appendChild(textOfA);
        List<NodePublic> expectedList = Arrays.asList(a);
        List<NodePublic> actualList = parserSimpleText.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testParseText() {
        String text = "This is the text of some tag";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);     
        TextPublic textDom = new TextPublic(text);
        List<NodePublic> expectedList = Arrays.asList(textDom);
        List<NodePublic> actualList = parserSimpleText.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testParseSimpleTextWithManyData() {
        String firstTextCont = "This is the text of some tag";
        String secondTextCont = "Content of tag";
        String thirdTextCont = "Last content";
        String text = String.format("%s<a></a>%s<a></a><a></a>%s", firstTextCont, secondTextCont, thirdTextCont);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);     
        TextPublic firstTextDom = new TextPublic(firstTextCont);
        TextPublic secondTextDom = new TextPublic(secondTextCont);
        TextPublic thirdTextDom = new TextPublic(thirdTextCont);
        List<NodePublic> expectedList = Arrays.asList(firstTextDom, new A(), secondTextDom, new A(), new A(), thirdTextDom);
        List<NodePublic> actualList = parserSimpleText.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testParseSimpleTextWithInvalidData() {
        String text = "<script></script>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);     
        List<NodePublic> expectedList = Arrays.asList();
        List<NodePublic> actualList = parserSimpleText.parse();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testParseSimpleTextWithBr() {
        String textCont = "Text of an page";
        String text = String.format("%s<br>", textCont);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);    
        TextPublic textDom = new TextPublic(textCont);
        Br br = new Br();
        List<NodePublic> expectedList = Arrays.asList(textDom, br);
        List<NodePublic> actualList = parserSimpleText.parse();
        assertEquals(expectedList, actualList);
    }
}
