package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface Element extends Node{
    static final short nodeType = ELEMENT_NODE;
    public String getTagName();
    public String getAttribute(String name);
    public void setAttribute(String name,String value);
    public void removeAttribute(String name);
    public Attr getAttributeNode(String name);
    public Attr setAttributeNode(Attr newAttr);
    public Attr removeAttributeNode(Attr oldAttr);
    public NodeList getElementsByTagName(String name);
    public String getAttributeNS(String namespaceURI,String localName);
    public void setAttributeNS(String namespaceURI,String qualifiedName,String value);
    public void removeAttributeNS(String namespaceURI,String localName);
    public Attr getAttributeNodeNS(String namespaceURI,String localName);
    public Attr setAttributeNodeNS(Attr newAttr);
    public NodeList getElementsByTagNameNS(String namespaceURI,String localName);
    public boolean hasAttribute(String name);
    public boolean hasAttributeNS(String namespaceURI,String localName);
    public TypeInfo getSchemaTypeInfo();
    public void setIdAttribute(String name,boolean isId);
    public void setIdAttributeNS(String namespaceURI,String localName,boolean isId);
    public void setIdAttributeNode(Attr idAttr,boolean isId);
}
