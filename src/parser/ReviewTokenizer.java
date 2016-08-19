package parser;

import dom_public.AttrPublic;
import tokenizer.Tokenizer;
import dom_public.TextPublic;
import element_token.AttributeToken;
import element_token.TagToken;
import element_token.TextToken;
import element_token.Token;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tokenizer.TokenizerAttribute.StateTokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ReviewTokenizer {

    private Tokenizer tokenizer;
    private Token token;
    private boolean editValue;
    private String NameTag;

    public ReviewTokenizer(Tokenizer tokenizer) {
        if (tokenizer != null) {
            this.tokenizer = tokenizer;
            token = tokenizer.next();
            editValue = false;
        } else {
            throw new ExceptionInInitializerError("The tokenizer is null");
        }
    }

    public boolean isExpectedTag(String expNameTag, boolean closeTag) {
        boolean isExpected = false;
        if (token instanceof TagToken) {
            TagToken tag = (TagToken) token;
            String nameTag = tag.getNameTag();
            boolean isCloseTag = tag.isCloseTag();
            isExpected = nameTag.equals(expNameTag) && isCloseTag == closeTag;
            token = isExpected ? tokenizer.next() : token;
        }
        return isExpected;
    }

    public TextPublic getText() {
        TextPublic text = null;
        if (token instanceof TextToken) {
            TextToken textToken = (TextToken) token;
            text = new TextPublic(textToken.getContent());
            token = tokenizer.next();
        }
        return text;
    }

    public List<AttrPublic> getListAttribute(List<String> listValidNameAttr) {
        Stack<AttrPublic> stackAttribute = new Stack<>();
        if (listValidNameAttr != null) {
            AttributeToken attrToken;
            while (token instanceof AttributeToken) {
                attrToken = (AttributeToken) token;
                stackAttribute = getValidAttr(attrToken, listValidNameAttr, stackAttribute);
                token = tokenizer.next();
            }
        }
        return stackAttribute;
    }

    @SuppressWarnings("unchecked")
    private Stack<AttrPublic> getValidAttr(AttributeToken attrToken, List<String> listValidNameAttr, Stack<AttrPublic> stackAttribute) {
        Stack<AttrPublic> stackAttr = ((Stack<AttrPublic>) stackAttribute.clone());
        StateTokenizer stateTok = attrToken.getState();
        String contAttrToken = attrToken.getContent();
        if (stateTok.equals(StateTokenizer.NAME) && listValidNameAttr.contains(contAttrToken)) {
            stackAttr.add(new AttrPublic(contAttrToken, "\"\""));
            editValue = true;
        } else if (stateTok.equals(StateTokenizer.VALUE) && editValue && isValidValue(attrToken) && !stackAttr.isEmpty()) {
            AttrPublic attribute = stackAttr.pop();
            String nameAttrToken = attribute.getName();
            contAttrToken = contAttrToken.startsWith("\"") ? contAttrToken : "\"" + contAttrToken + "\"";
            stackAttr.add(new AttrPublic(nameAttrToken, contAttrToken));
            editValue = false;
        }
        return stackAttr;
    }
    
    public List<String> getListValidNameAttribute(String nameTag){
        return tokenizer.getDictionary().getListNameAttr(nameTag);
    }

    private boolean isValidValue(AttributeToken attrToken) {
        boolean isValid = false;
        String text = attrToken.getContent();
        Pattern pattern = Pattern.compile("[\\d\\s\\w=()\".//?#:,_-]*");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            int end = matcher.end();
            isValid = end == text.length();
        }
        return isValid;
    }

    public String getErrorInform() {
        return tokenizer.getErrorInform();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.tokenizer);
        hash = 23 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            final ReviewTokenizer other = (ReviewTokenizer) obj;
            isEqual = this.tokenizer.equals(other.tokenizer) && token.equals(other.token);
        }
        return isEqual;
    }

}
