package tokenizer;

import element_token.AttributeToken;
import element_token.Token;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public class TokenizerAttribute implements Iterator {

    public enum StateTokenizer {

        NAME, VALUE
    };
    private String textAttribute;
    private StateTokenizer stateTokenizer;

    public TokenizerAttribute() {
        this.textAttribute = null;
        stateTokenizer = StateTokenizer.NAME;
    }

    public void configureState(String textAttribute) {
        this.textAttribute = textAttribute;
        stateTokenizer = StateTokenizer.NAME;
    }

    @Override
    public boolean hasNext() {
        return textAttribute != null && !textAttribute.equals("") && !textAttribute.startsWith("/>")
                                        && !textAttribute.startsWith(">");
    }

    @Override
    public Token next() {
        Token token = null;
        if (hasNext()) {
            textAttribute = textAttribute.trim();
            switch (stateTokenizer) {
                case NAME:
                    token = tokenizeName();
                    break;
                case VALUE:
                    token = tokenizeValue();
                    break;
            }
        }
        return token;
    }

    private Token tokenizeName() {
        AttributeToken token = null;
        String name = getTextAttribute(true);
        if (!name.equals("")) {
            name = name.replace('/', ' ').trim();
            token = new AttributeToken(name.trim(), stateTokenizer, true);
            int sizeName = name.length();
            textAttribute = textAttribute.substring(sizeName).trim();
            change_state();
        }
        return token;
    }

    public String getTextAttribute() {
        return textAttribute == null ? textAttribute : textAttribute.trim();
    }

    private void change_state() {
        if (textAttribute.startsWith("=")) {
            textAttribute = textAttribute.substring(1);
            stateTokenizer = StateTokenizer.VALUE;
        }
    }

    private Token tokenizeValue() {
        AttributeToken token = null;
        String value;
        char character = textAttribute.charAt(0);
        boolean isValid = true;
        if (character == '\"') {
            int indCloseDouQuote = textAttribute.indexOf(character, 1);
            boolean closeDouQuote = indCloseDouQuote >= 0;
            if (closeDouQuote) {
                indCloseDouQuote += 1;
            } else {
                indCloseDouQuote = textAttribute.length();
                isValid = false;
            }
            value = textAttribute.substring(0, indCloseDouQuote);
            textAttribute = textAttribute.substring(indCloseDouQuote);
        } else {
            value = getTextAttribute(false);
            textAttribute = textAttribute.substring(value.length());
        }
        if (!value.equals("")) {
            token = new AttributeToken(value, StateTokenizer.VALUE, isValid);
        }
        stateTokenizer = stateTokenizer == StateTokenizer.VALUE?StateTokenizer.NAME:stateTokenizer;
        return token;
    }

    private String getTextAttribute(boolean isName) {
        int sizeText = textAttribute.length();
        int posChar = 0;
        char character;
        boolean followConcat = true;
        boolean isEndAngBrac;
        while (posChar < sizeText && followConcat) {
            character = textAttribute.charAt(posChar);
            isEndAngBrac = character == '>';
            if (isEndAngBrac) {
                followConcat = false;
            } else {
                followConcat = character != ' ';
                if (isName && followConcat && posChar > 0) {
                    followConcat = character != '/' && character != '=';
                }
            }
            posChar = followConcat ? ++posChar : posChar;
        }
        String text = textAttribute.substring(0, posChar);
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.textAttribute);
        hash = 17 * hash + Objects.hashCode(this.stateTokenizer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            final TokenizerAttribute other = (TokenizerAttribute) obj;
            boolean textAttrNull = this.textAttribute == null;
            isEqual =  (textAttrNull && other.textAttribute == null) ||
                    !textAttrNull && this.textAttribute.equals(other.textAttribute) && this.stateTokenizer == other.stateTokenizer;
        }
        return isEqual;
    }
}