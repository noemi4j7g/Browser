package tokenizer;

import element_token.AttributeToken;
import element_token.Token;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import tokenizer.TokenizerAttribute.StateTokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class TokenizerAttributeTest {

    @Test
    public void testTokenizeWithNullData() {
        String data = null;
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        Token actualAttrToken = tokAttribute.next();
        assertNull(actualAttrToken);
    }

    @Test
    public void testTokenizeWithEmptyData() {
        String data = "";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        Token actualAttrToken = tokAttribute.next();
        assertNull(actualAttrToken);
    }
    
    @Test
    public void testEqualTokenizer() {
        String dataOne = "name";
        TokenizerAttribute tokAttributeOne = new TokenizerAttribute();
        tokAttributeOne.configureState(dataOne);
        String dataTwo = dataOne;
        TokenizerAttribute tokAttributeTwo = new TokenizerAttribute();
        tokAttributeTwo.configureState(dataTwo);
        assertEquals(tokAttributeOne, tokAttributeTwo);
    }
    
    @Test
    public void testDifferentTokenizer() {
        String dataOne = "name";
        TokenizerAttribute tokAttributeOne = new TokenizerAttribute();
        tokAttributeOne.configureState(dataOne);
        String dataTwo = "value";
        TokenizerAttribute tokAttributeTwo = new TokenizerAttribute();
        tokAttributeTwo.configureState(dataTwo);
        assertNotSame(tokAttributeOne, tokAttributeTwo);
    }

    @Test
    public void testTokenizeWithSpace() {
        String data = "        name  ";
        String content = "name";
        StateTokenizer state = StateTokenizer.NAME;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }

    @Test
    public void testTokenizeNameStartingWithBackSlash() {
        String data = "/name";
        String content = "name";
        StateTokenizer state = StateTokenizer.NAME;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }
    
    @Test
    public void testTokenizeWithIntemediateBackSlash() {
        String data = "id/name";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String contFirstAttr = "id";
        StateTokenizer stateFirstAttr = StateTokenizer.NAME;
        boolean isValidFirstAttr = true;
        AttributeToken expFirstAttr = new AttributeToken(contFirstAttr, stateFirstAttr, isValidFirstAttr);
        Token actFirstAttr = tokAttribute.next();
        assertEquals(expFirstAttr, actFirstAttr);
        
        String contSecondAttr = "name";
        StateTokenizer stateSecondAttr = StateTokenizer.NAME;
        boolean isValidSecondAttr = true;
        AttributeToken expSecondAttr = new AttributeToken(contSecondAttr, stateSecondAttr, isValidSecondAttr);
        Token actSecondAttr = tokAttribute.next();
        assertEquals(expSecondAttr, actSecondAttr);
    }

    @Test
    public void testTokenizeWithQuoteAsName() {
        String data = "\"=\"nameTag\"";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
       
        String content = "\"";
        StateTokenizer state = StateTokenizer.NAME;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }

    @Test
    public void testTokenizeWithAnySymbolAsName() {
        String data = "+=\"nameTag\"";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String content = "+";
        StateTokenizer state = StateTokenizer.NAME;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }

    @Test
    public void testTokenizeNameStartingWithEqual() {
        String data = "=\"nameTag\"";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String content = data;
        StateTokenizer state = StateTokenizer.NAME;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        Token actAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actAttrToken);
    }

    @Test
    public void testTokenizeWithoutEqual() {
        String data = "name \"nameTag\"";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String contFirstAttr = "name";
        StateTokenizer stateFirstAttr = StateTokenizer.NAME;
        boolean isValidFirstAttr = true;
        AttributeToken expFirstAttr = new AttributeToken(contFirstAttr, stateFirstAttr, isValidFirstAttr);
        Token actFirstAttr = tokAttribute.next();
        assertEquals(expFirstAttr, actFirstAttr);
        
        String contSecondAttr = "\"nameTag\"";
        StateTokenizer stateSecondAttr = StateTokenizer.NAME;
        boolean isValidSecondAttr = true;
        AttributeToken expSecondAttr = new AttributeToken(contSecondAttr, stateSecondAttr, isValidSecondAttr);
        Token actSecondAttr = tokAttribute.next();
        assertEquals(expSecondAttr, actSecondAttr);
    }

    @Test
    public void testTokenizeValueWithDoubleQuote() {
        String data = "   name   =  \"nameTag\"";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String content = "\"nameTag\"";
        StateTokenizer state = StateTokenizer.VALUE;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        tokAttribute.next();
        Token actAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actAttrToken);
    }

    @Test
    public void testTokenizeValueWithoutCloseQuote() {
        String data = "name=\"nameTag";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String content = "\"nameTag";
        StateTokenizer state = StateTokenizer.VALUE;
        boolean isValid = false;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        tokAttribute.next();
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }

    @Test
    public void testTokenizeValueWithoutDoubleQuote() {
        String data = "name=nameTag";
        TokenizerAttribute tokAttribute = new TokenizerAttribute();
        tokAttribute.configureState(data);
        
        String content = "nameTag";
        StateTokenizer state = StateTokenizer.VALUE;
        boolean isValid = true;
        AttributeToken expectedAttrToken = new AttributeToken(content, state, isValid);
        tokAttribute.next();
        Token actualAttrToken = tokAttribute.next();
        assertEquals(expectedAttrToken, actualAttrToken);
    }
    
}
