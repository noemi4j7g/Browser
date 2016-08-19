package parser;

import dom_public.AttrPublic;
import dom_public.TextPublic;
import java.util.List;
import java.util.Stack;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ReviewTokenizerTest {
    
    @Test (expected = ExceptionInInitializerError.class)
    public void testInitializeWithNullData(){
        Tokenizer tokenizer = null;
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
    }
    
    @Test
    public void testEqualsClients(){
        String textDocOne = "<html><body></body></html>";
        Tokenizer tokenizerOne = new Tokenizer(textDocOne);
        ReviewTokenizer reviewTokOne = new ReviewTokenizer(tokenizerOne);
        String textDocTwo = textDocOne;
        Tokenizer tokenizerTwo = new Tokenizer(textDocTwo);
        ReviewTokenizer reviewTokTwo = new ReviewTokenizer(tokenizerTwo);
        assertEquals(reviewTokOne, reviewTokTwo);
    }
    
    @Test
    public void testDifferentsClients(){
        String textDocOne = "<html><head></head><body></body></html>";
        Tokenizer tokenizerOne = new Tokenizer(textDocOne);
        ReviewTokenizer reviewTokOne = new ReviewTokenizer(tokenizerOne);
        String textDocTwo = "<html><body></body></html>";
        Tokenizer tokenizerTwo = new Tokenizer(textDocTwo);
        ReviewTokenizer reviewTokTwo = new ReviewTokenizer(tokenizerTwo);
        assertNotSame(reviewTokOne, reviewTokTwo);
    }
    
    @Test
    public void testValidTag() {
        String textDoc = "<html>";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        String expNameTag = "html";
        boolean closeTag = false;
        assertTrue(reviewTok.isExpectedTag(expNameTag, closeTag));
    }
    
    @Test
    public void testInvalidTag() {
        String textDoc = "<tag>";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        String expNameTag = "tag";
        boolean closeTag = false;
        assertFalse(reviewTok.isExpectedTag(expNameTag, closeTag));
    }
    
    //Review with equals many name of attributes
    
    @Test
    public void testGetListAttrWithDoubleQuote() {
        String nameAttrId = "id";
        String valueAttrId = "\"idBody\"";
        String nameAttrClass = "class";
        String valueAttrClass = "\"classBody\"";
        String textDoc = String.format("<body %s=%s %s=%s>",nameAttrId, valueAttrId, nameAttrClass, valueAttrClass);
        Tokenizer tokenizer = new Tokenizer(textDoc);
        tokenizer.next();
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        List<String> listValidNameAttr = reviewTok.getListValidNameAttribute("body");
        AttrPublic attrId = new AttrPublic(nameAttrId, valueAttrId);
        AttrPublic attrClass = new AttrPublic(nameAttrClass, valueAttrClass);
        List<AttrPublic> expectedListAttr = new Stack<>();
        expectedListAttr.add(attrId);
        expectedListAttr.add(attrClass);
        List<AttrPublic> actualListAttr = reviewTok.getListAttribute(listValidNameAttr);
        assertEquals(expectedListAttr, actualListAttr);
    }
    
    @Test
    public void testGetListAttrWithoutDoubleQuote() {
        String nameAttrId = "id";
        String valueAttrId = "idBody";
        String nameAttrClass = "class";
        String valueAttrClass = "classBody";
        String textDoc = String.format("<body %s=%s %s=%s>",nameAttrId, valueAttrId, nameAttrClass, valueAttrClass);
        Tokenizer tokenizer = new Tokenizer(textDoc);
        tokenizer.next();
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        List<String> listValidNameAttr = reviewTok.getListValidNameAttribute("body");
        AttrPublic attrId = new AttrPublic(nameAttrId, "\"" + valueAttrId + "\"");
        AttrPublic attrClass = new AttrPublic(nameAttrClass, "\"" + valueAttrClass + "\"");
        List<AttrPublic> expectedListAttr = new Stack<>();
        expectedListAttr.add(attrId);
        expectedListAttr.add(attrClass);
        List<AttrPublic> actualListAttr = reviewTok.getListAttribute(listValidNameAttr);
        assertEquals(expectedListAttr, actualListAttr);
    }
    
    @Test
    public void testGetListAttrOfInvalidData() {
        String nameAttrId = "nameAttr";
        String valueAttrId = "\"nameHead\"";
        String textDoc = String.format("<head %s=%s></head>",nameAttrId, valueAttrId);
        Tokenizer tokenizer = new Tokenizer(textDoc);
        tokenizer.next();
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        List<String> listValidNameAttr = tokenizer.getDictionary().getListNameAttr("head");
        List<AttrPublic> expectedListAttr = new Stack<>();
        List<AttrPublic> actualListAttr = reviewTok.getListAttribute(listValidNameAttr);
        assertEquals(expectedListAttr, actualListAttr);
    }
    
    @Test
    public void testGetText() {
        String textDoc = "Text of one tag";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        TextPublic expectedText = new TextPublic(textDoc);
        TextPublic actualText = reviewTok.getText();
        assertEquals(expectedText, actualText);
    }
    
    @Test
    public void testGetInexistentText() {
        String textDoc = "<body>Text of one tag</body>";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        TextPublic text = reviewTok.getText();
        assertNull(text);
    }
      
    @Test
    public void testCloseTag() {
        String textDoc = "</html>";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        String nameTag = "html";
        boolean isCloseTag = true;
        assertTrue(reviewTok.isExpectedTag(nameTag, isCloseTag));
    }
    
    @Test
    public void testCloseTagWithInvalidData() {
        String textDoc = "Text of tag";
        Tokenizer tokenizer = new Tokenizer(textDoc);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        String nameTag = "html";
        boolean isCloseTag = true;
        assertFalse(reviewTok.isExpectedTag(nameTag, isCloseTag));
    }
    
}
