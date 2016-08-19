package element_token;

import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public class TagToken implements Token{
    
    private final String nameTag;
    private final boolean closeTag;

    public TagToken(String nameTag, boolean isCloseTag){
        this.nameTag = nameTag.trim();
        this.closeTag = isCloseTag;
    }
    
    public String getNameTag() {
        return nameTag;
    }

    public boolean isCloseTag() {
        return closeTag;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nameTag);
        hash = 67 * hash + (this.closeTag ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj != null && getClass() == obj.getClass()){
            TagToken other = (TagToken) obj;
            isEqual = this.nameTag.equals(other.nameTag) && 
                        this.closeTag == other.closeTag;
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Tag{" + "nameTag=" + nameTag + ", closeTag=" + closeTag + '}';
    }

}
