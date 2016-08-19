package tokenizer;

import element_token.AttributeToken;
import element_token.TagToken;
import element_token.TextToken;
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
public class TokenizerTest {
    
    @Test
    public void testRemoveIntermediateSpaces() {
        String string = " the text with            \n    \r    \t     many    spaces ";
        String expectedString = "the text with many spaces";
        Tokenizer tokenizer = new Tokenizer("<html>");
        String actualString = tokenizer.removeIntermediateSpaces(string);
        assertEquals(expectedString, actualString);
    }

    @Test
    public void testRemoveIntermediateSpacesWithTextBetweenDoubleQuote() {
        String string = " the text with            \"\n\"     many    spaces ";
        String expectedString = "the text with \" \" many spaces";
        Tokenizer tokenizer = new Tokenizer("<html>");
        String actualString = tokenizer.removeIntermediateSpaces(string);
        assertEquals(expectedString, actualString);
    }

    @Test
    public void testRemoveIntermediateSpacesWithTextDoubleSlash() {
        String string = " the text with                 many  \\n  spaces ";
        String expectedString = "the text with many \\n spaces";
        Tokenizer tokenizer = new Tokenizer("<html>");
        String actualString = tokenizer.removeIntermediateSpaces(string);
        assertEquals(expectedString, actualString);
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void testTokenizeNullData() {
        String data = null;
        Tokenizer tokenizer = new Tokenizer(data);
    }

    @Test
    public void testTokenizeWithSpaces() {
        String data = "\n   \t  \r";
        Tokenizer tokenizer = new Tokenizer(data);
        Token nextToken = tokenizer.next();
        assertNull(nextToken);
    }

    @Test
    public void testTokenizeWithEmptyData() {
        String data = "";
        Tokenizer tokenizer = new Tokenizer(data);
        Token nextToken = tokenizer.next();
        assertNull(nextToken);
    }
    
    @Test
    public void testEqualTokenizer() {
        String dataOne = "text of tag";
        Tokenizer tokenizerOne = new Tokenizer(dataOne);
        String dataTwo = dataOne;
        Tokenizer tokenizerTwo = new Tokenizer(dataTwo);
        assertEquals(tokenizerOne, tokenizerTwo);
    }
    
    @Test
    public void testDifferentTokenizer() {        
        String dataOne = "text of tag";
        Tokenizer tokenizerOne = new Tokenizer(dataOne);
        String dataTwo = "<html></html>";
        Tokenizer tokenizerTwo = new Tokenizer(dataTwo);
        assertNotSame(tokenizerOne, tokenizerTwo);
    }

    @Test
    public void testTokenizeValidTagForDOM() {
        String data = "<html>";
        boolean isCloseTag = false;
        Token expectedToken = new TagToken("html", isCloseTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeValidTagForDOMWithSpaces() {
        String data = "     \n     \t\t    <html>";
        boolean isCloseTag = false;
        Token expectedToken = new TagToken("html", isCloseTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeValidTagWithoutCloseAngleBracket() {
        String data = "<link/>";
        boolean isCloseTag = false;
        Token expectedToken = new TagToken("link", isCloseTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeValidTagForDOMWithMoreTags() {
        String data = "<html></html>";
        boolean isCloseTag = false;
        Token expectedToken = new TagToken("html", isCloseTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeValidUppeTagForDOM() {
        String data = "<BODY>";
        boolean isCloseTag = false;
        Token expectedToken = new TagToken("body", isCloseTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeInvalidTagForDOM() {
        String nameTag = "tag";
        String data = String.format("<%s>", nameTag);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertNull(actualToken);
        String pathGrammar = "src/docs/grammar.txt";
        String expectedInform = String.format("The tag: '%s' is invalid or is not in the specified grammar.\n"
                        + "Review the grammar in %s in the project 'Browser'.", nameTag, pathGrammar);
        assertEquals(expectedInform, tokenizer.getErrorInform());
    }

    @Test
    public void testTokenizeValidCloseTagForDOM() {
        String data = "</div>";
        Tokenizer tokenizer = new Tokenizer(data);
        boolean isCloseTag = true;
        Token expectedToken = new TagToken("div", isCloseTag);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeSimpleText() {
        String data = "It is the text of an tag";
        Token expectedToken = new TextToken(data);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeSimpleTextWithManySpacesIn() {
        String data = "It is     the       text      of an     tag";
        String expectedData = "It is the text of an tag";
        Token expectedToken = new TextToken(expectedData);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeSimpleTextWithEndingTag() {
        String expectedData = "It is the text of an tag";
        String data = expectedData + "</html>";
        Token expectedToken = new TextToken(expectedData);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeSimpleTextWithInitialSpaces() {
        String data = "     \n    \t \t It is the text of an tag";
        String expectedData = "It is the text of an tag";
        Token expectedToken = new TextToken(expectedData);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeCompoundText() {
        String data = "It is the text of an tag with many characters: <>*~.:%,-=;!#\"&";
        Token expectedToken = new TextToken(data);
        Tokenizer tokenizer = new Tokenizer(data);
        Token actualToken = tokenizer.next();
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testTokenizeManyTag() {
        String data = "<html><head></head></html>";
        Tokenizer tokenizer = new Tokenizer(data);

        Token expOpenTagHtml = new TagToken("html", false);
        Token actOpenTagHtml = tokenizer.next();
        assertEquals(expOpenTagHtml, actOpenTagHtml);

        Token expOpenTagHead = new TagToken("head", false);
        Token actOpenTagHead = tokenizer.next();
        assertEquals(expOpenTagHead, actOpenTagHead);

        Token expCloseTagHead = new TagToken("head", true);
        Token actCloseTagHead = tokenizer.next();
        assertEquals(expCloseTagHead, actCloseTagHead);

        Token expCloseTagHtml = new TagToken("html", true);
        Token actCloseTagHtml = tokenizer.next();
        assertEquals(expCloseTagHtml, actCloseTagHtml);
    }

    @Test
    public void testTokenizeTagWithText() {
        String data = "<div>text</div>";
        Tokenizer tokenizer = new Tokenizer(data);

        Token expTokenTag = new TagToken("div", false);
        Token actTokenTag = tokenizer.next();
        assertEquals(expTokenTag, actTokenTag);

        Token expTokenText = new TextToken("text");
        Token actTokenText = tokenizer.next();
        assertEquals(expTokenText, actTokenText);

        Token expTokenCloseTag = new TagToken("div", true);
        Token actTokenCloseTag = tokenizer.next();
        assertEquals(expTokenCloseTag, actTokenCloseTag);
    }

    @Test
    public void testTokenizeAutomaticallyClosedTagWithAttribute() {
        String data = "<link rel=\"stylesheet\"/>";
        Tokenizer tokenizer = new Tokenizer(data);

        Token expTokenTag = new TagToken("link", false);
        Token actTokenTag = tokenizer.next();
        assertEquals(expTokenTag, actTokenTag);

        String contentName = "rel";
        StateTokenizer stateName = StateTokenizer.NAME;
        boolean isValidName = true;
        Token expAttrName = new AttributeToken(contentName, stateName, isValidName);
        Token actAttrNameToken = tokenizer.next();
        assertEquals(expAttrName, actAttrNameToken);

        String contentValue = "\"stylesheet\"";
        StateTokenizer stateValue = StateTokenizer.VALUE;
        boolean isValidValue = true;
        Token expAttrValue = new AttributeToken(contentValue, stateValue, isValidValue);
        Token actAttrValueToken = tokenizer.next();
        assertEquals(expAttrValue, actAttrValueToken);
    }

    @Test
    public void testTokenizeFullTag() {
        String data = "<div name =\"attributeDiv\">text</div>";
        Tokenizer tokenizer = new Tokenizer(data);

        Token expTokenTag = new TagToken("div", false);
        Token actTokenTag = tokenizer.next();
        assertEquals(expTokenTag, actTokenTag);

        String contentName = "name";
        StateTokenizer stateName = StateTokenizer.NAME;
        boolean isValidName = true;
        Token expAttrName = new AttributeToken(contentName, stateName, isValidName);
        Token actAttrNameToken = tokenizer.next();
        assertEquals(expAttrName, actAttrNameToken);

        String contentValue = "\"attributeDiv\"";
        StateTokenizer stateValue = StateTokenizer.VALUE;
        boolean isValidValue = true;
        Token expAttrValue = new AttributeToken(contentValue, stateValue, isValidValue);
        Token actAttrValueToken = tokenizer.next();
        assertEquals(expAttrValue, actAttrValueToken);

        Token expTokenText = new TextToken("text");
        Token actTokenText = tokenizer.next();
        assertEquals(expTokenText, actTokenText);

        Token expTokenCloseTag = new TagToken("div", true);
        Token actTokenCloseTag = tokenizer.next();
        assertEquals(expTokenCloseTag, actTokenCloseTag);
    }

}
