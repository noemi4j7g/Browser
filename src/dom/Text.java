package dom;

/**
 *
 * @author arquitectura de software II-2015
 */

public interface Text extends CharacterData{
    public Text splitText(int offset);
    public boolean isElementContentWhitespace();
    public String getWholeText();
    public Text replaceWholeText(String content);
}
