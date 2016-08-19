package element_token;

import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public class TextToken implements Token{
    
    private final String content;
  
    public TextToken(String content){
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public String toString() {
        return "TextToken{" + "content=" + content + '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            TextToken other = (TextToken) obj;
            isEqual = this.content.equals(other.content);
        }
        return isEqual;
    }

}
