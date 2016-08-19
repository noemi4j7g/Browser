package tokenizer;

import element_token.AttributeToken;
import element_token.TagToken;
import element_token.TextToken;
import element_token.Token;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author arquitectura de software II-2015
 */
public class Tokenizer implements Iterator {

    public enum StateTokenizer {

        TAG, ATTRIBUTE, TEXT
    };
    private static final String PATHFILE = "/docs/DataDictionary.txt";
    private static final String PATHGRAMMAR = "src/docs/grammar.txt";
    private TokenizerAttribute tokenizerAttribute;
    private StateTokenizer stateTokenizer;
    private Dictionary dictionary;
    private String textDoc;
    private StringBuilder errorInform;
    private boolean reportError;

    public Tokenizer(String textDoc) throws ExceptionInInitializerError {
        if (textDoc != null) {
            textDoc = removeIntermediateSpaces(textDoc);
            this.textDoc = textDoc;
            errorInform = new StringBuilder();
            reportError = false;
            if (!this.textDoc.equals("")) {
                try {
                    initialize();
                } catch (IOException ioException) {
                    System.err.println(ioException);
                }
            }
        } else {
            throw new ExceptionInInitializerError("The text is null");
        }
    }

    public String removeIntermediateSpaces(String string) {
        return string != null ? string.replaceAll("\\s+", " ").trim() : string;
    }

    private void initialize() throws IOException {
        tokenizerAttribute = new TokenizerAttribute();
        dictionary = new Dictionary(PATHFILE);
        stateTokenizer = determineStateTokenizer(this.textDoc);
    }

    private Token tokenizeText() {
        Token token = null;
        reportError = false;
        String tag = getTag(textDoc);
        int index;
        if (tag != null) {
            index = textDoc.indexOf(tag);
            stateTokenizer = StateTokenizer.TAG;
            token = index == 0 ? tokenizeTag() : token;
        } else {
            index = textDoc.length();
        }
        if (index > 0 && token == null) {
            String content = (textDoc.substring(0, index)).trim();
            textDoc = textDoc.substring(index).trim();
            token = new TextToken(content);
        }
        return token;
    }

    private String getTag(String string) {
        String nameTag = null;
        if (string != null && !string.equals("")) {
            Pattern pattern = Pattern.compile("<[/]?[a-zA-Z]+[0-9]*");
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                nameTag = string.substring(matcher.start(), matcher.end()).trim();
            }else if(reportError){
                String inform = "The text doesn't match with the expected text.\nExpected an tag p.e: '<nameTag>' or </nameTag>. ";
                errorInform.append(inform);
                reportError = false;
            }
        }
        return nameTag;
    }

    private Token tokenizeTag() {
        TagToken tag = null;
        reportError = true;
        String nameTag = getTag(textDoc);
        if (nameTag != null) {
            int index = textDoc.indexOf(nameTag) + nameTag.length();
            textDoc = textDoc.substring(index).trim();
            int jump = 1;
            boolean isCloseAngBracket = nameTag.startsWith("</");
            boolean closeTag = closeTag();
            if (!isCloseAngBracket && !closeTag) {
                stateTokenizer = StateTokenizer.ATTRIBUTE;
            }else{
                jump = isCloseAngBracket? jump + 1: jump;
                stateTokenizer = StateTokenizer.TEXT;
            }
            nameTag = nameTag.substring(jump).toLowerCase();
            if (dictionary.existNameTag(nameTag)) {
                tag = new TagToken(nameTag, isCloseAngBracket);
            }else{
                String inform = String.format("The tag: '%s' is invalid or is not in the specified grammar.\nReview the grammar in %s in the project 'Browser'.", nameTag, PATHGRAMMAR);
                errorInform.append(inform);
            }
        }
        return tag;
    }

    private boolean closeTag() {
        boolean closeTagWithSlash = textDoc.startsWith("/>");
        boolean closeTagWithoutSlash = textDoc.startsWith(">");
        boolean closeTag = closeTagWithSlash || closeTagWithoutSlash;
        if(closeTag){
            textDoc = closeTagWithSlash ? textDoc.substring(2) : textDoc.substring(1);
            textDoc = textDoc.trim();
        }
        return closeTag;
    }

    public String getErrorInform() {
        return errorInform.toString();
    }

    private Token tokenizeAttribute() {
        if (tokenizerAttribute.getTextAttribute() == null) {
            tokenizerAttribute.configureState(textDoc);
        }
        Token attributeToken = tokenizerAttribute.next();
        textDoc = tokenizerAttribute.getTextAttribute();
        if(attributeToken != null){
            AttributeToken attrToken = (AttributeToken) attributeToken;
            if(!attrToken.isValid()){
                String report = String.format("The attribute: %s of the tag is not valid.", attrToken.getContent());
                errorInform.append(report);
            }
        }
        if (!tokenizerAttribute.hasNext()) {
            stateTokenizer = StateTokenizer.TEXT;
            tokenizerAttribute.configureState(null);
            closeTag();
        }
        return attributeToken;
    }

    @Override
    public boolean hasNext() {
        return !textDoc.equals("");
    }

    @Override
    public Token next() {
        Token token = null;
        if (hasNext()) {
            switch (stateTokenizer) {
                case TEXT:
                    token = tokenizeText();
                    break;
                case TAG:
                    token = tokenizeTag();
                    break;
                case ATTRIBUTE:
                    token = tokenizeAttribute();
                    break;
            }
        }
        return token;
    }

    private StateTokenizer determineStateTokenizer(String string) {
        StateTokenizer stateTok = StateTokenizer.TEXT;
        Pattern pattern = Pattern.compile("^<[/]?[a-zA-Z]+[0-9]*");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            stateTok = StateTokenizer.TAG;
        }
        return stateTok;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tokenizerAttribute);
        hash = 97 * hash + Objects.hashCode(this.stateTokenizer);
        hash = 97 * hash + Objects.hashCode(this.dictionary);
        hash = 97 * hash + Objects.hashCode(this.textDoc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            final Tokenizer other = (Tokenizer) obj;
            boolean tokAttrNull = this.tokenizerAttribute == null;
            boolean equalTokAttr = (tokAttrNull && other.tokenizerAttribute == null) ||
                    !tokAttrNull && this.tokenizerAttribute.equals(other.tokenizerAttribute);
            boolean equalStateTok = equalTokAttr && this.stateTokenizer == other.stateTokenizer;
            isEqual = equalStateTok && this.textDoc.equals(other.textDoc);
        }
        return isEqual;
    }

}