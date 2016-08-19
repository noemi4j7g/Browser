package dom;

/**
 *
 * @author arquitectura de software II-2015
 */

public interface Node {
    public static final short ELEMENT_NODE              = 1;
    public static final short ATTRIBUTE_NODE            = 2;
    public static final short TEXT_NODE                 = 3;
    public static final short CDATA_SECTION_NODE        = 4;
    public static final short ENTITY_REFERENCE_NODE     = 5;
    public static final short ENTITY_NODE               = 6;
    public static final short PROCESSING_INSTRUCTION_NODE = 7;
    public static final short COMMENT_NODE              = 8;
    public static final short DOCUMENT_NODE             = 9;
    public static final short DOCUMENT_TYPE_NODE        = 10;
    public static final short DOCUMENT_FRAGMENT_NODE    = 11;
    public static final short NOTATION_NODE             = 12;

    public Node appendChild(Node newChild);
    public String getNodeName();
    public String getNodeValue();
    public void setNodeValue(String newValue);
    public NodeList getChildNodes();
    public short getNodeType();
    public Node getParentNode();
    public Node getFirstChild();
    public Node getLastChild();
    public Node getPreviousSibling();
    public Node getNextSibling();
    public NamedNodeMap getAttributes();
    public Document getOwnerDocument();
    public Node insertBefore(Node newChild, Node refChild);
    public Node replaceChild(Node newChild, Node oldChild);
    public Node removeChild(Node oldChild);
    public boolean hasChildNodes();
    public Node cloneNode(boolean deep);
    public void normalize();
    public boolean isSupported(String feature,String version);
    public String getNamespaceURI();
    public String getPrefix();
    public void setPrefix(String prefix);
    public String getLocalName();
    public boolean hasAttributes();
    public String getBaseURI();
    public short compareDocumentPosition(Node other);
    public String getTextContent();
    public void setTextContent(String textContent);
    public boolean isSameNode(Node other);
    public String lookupPrefix(String namespaceURI);
    public boolean isDefaultNamespace(String namespaceURI);
    public String lookupNamespaceURI(String prefix);
    public boolean isEqualNode(Node arg);
    public Object getFeature(String feature, String version);
    public Object setUserData(String key,Object data,UserDataHandler handler);
    public Object getUserData(String key);

}
