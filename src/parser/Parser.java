package parser;

import dom.DOMException;
import dom.Node;
import dom_public.AttrPublic;
import dom_public.ElementPublic;
import dom_public.NodePublic;
import dom_public.TextPublic;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public abstract class Parser {

    protected ReviewTokenizer reviewTok;

    public Parser(ReviewTokenizer reviewTok) {
        if (reviewTok != null) {
            this.reviewTok = reviewTok;
        } else {
            throw new ExceptionInInitializerError("Error, the data is null");
        }
    }

    public ElementPublic addAttrToElement(ElementPublic element) {
        ElementPublic clonedElement = null;
        if (element != null) {
            clonedElement = element.cloneNode(true);
            String nameElement = element.getNodeName();
            List<String> listNameAttr = reviewTok.getListValidNameAttribute(nameElement);
            List<AttrPublic> listAttr = reviewTok.getListAttribute(listNameAttr);
            for (AttrPublic attribute : listAttr) {
                clonedElement.setAttributeNode(attribute);
            }
        }
        return clonedElement;
    }

    public ElementPublic editElement(ElementPublic element, List<NodePublic> listNode) {
        ElementPublic editedElement = element.cloneNode(true);
        if (editedElement != null && listNode != null) {
            editedElement = element.cloneNode(true);
            Node node;
            for (NodePublic nodePublic : listNode) {
                if (nodePublic instanceof TextPublic) {
                    node = (TextPublic) nodePublic;
                } else {
                    node = (ElementPublic) nodePublic;
                }
                editedElement.appendChild(node);
            }
        }
        return editedElement;
    }

    public void verifyCloseOfTag(String nameTag) {
        boolean isCloseTag = true;
        boolean isCloseTagElement = reviewTok.isExpectedTag(nameTag, isCloseTag);
        if (!isCloseTagElement) {
            String errorInform = reviewTok.getErrorInform();
            throw new DOMException((short) 12, String.format("class Parser. Error of syntax, expected \"</%s>\". %s", nameTag, errorInform));
        }
    }

    public abstract Object parse();

}
