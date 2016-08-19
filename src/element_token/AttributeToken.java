package element_token;

import java.util.Objects;
import tokenizer.TokenizerAttribute.StateTokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class AttributeToken implements Token {

    private final String content;
    private final boolean isValid;
    private final StateTokenizer state;

    public AttributeToken(String content, StateTokenizer state, boolean isValid) {
        this.content = content;
        this.isValid = isValid;
        this.state = state;    
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.content);
        hash = 97 * hash + (this.isValid ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AttributeToken other = (AttributeToken) obj;
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (this.isValid != other.isValid) {
            return false;
        }
        return this.state == other.state;
    }

    @Override
    public String toString() {
        return "AttributeToken{" + "content=" + content + ", isValid=" + isValid + ", state=" + state + '}';
    }

    public StateTokenizer getState() {
        return state;
    }

    public String getContent() {
        return content;
    }

    public boolean isValid() {
        return isValid;
    }

}
