package dom_public;

//import org.w3c.dom.*;
import dom.Attr;
import dom.Document;
import dom.Element;
import dom.NamedNodeMap;
import dom.Node;
import dom.NodeList;
import dom.TypeInfo;
import dom.UserDataHandler;
import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public abstract class ElementPublic implements Element, NodePublic {

    private final NamedNodeMapPublic attributes;
    private final NodeListPublic children;
    private final String nodeName;
    private String nodeValue;

    public ElementPublic(String nodeName) {
        children = new NodeListPublic();
        this.nodeName = nodeName;
        nodeValue = "";
        attributes = new NamedNodeMapPublic();
    }

    @Override
    public Node appendChild(Node newChild) {
        Node res = newChild;
        children.add(newChild);
        return res;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getNodeValue() {
        return nodeValue;
    }

    @Override
    public void setNodeValue(String newValue) {
        nodeValue = newValue;
    }

    @Override
    public NodeList getChildNodes() {
        return children;
    }

    @Override
    public Node removeChild(Node oldChild) {
        Node nodeRemoved = children.remove(oldChild);
        return nodeRemoved;
    }

    @Override
    public String toString() {
        return "Elemento{" + "tagname=" + nodeName + ", attributes: " + attributes + ", children: " + children + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.attributes);
        hash = 59 * hash + Objects.hashCode(this.children);
        hash = 59 * hash + Objects.hashCode(this.nodeName);
        hash = 59 * hash + Objects.hashCode(this.nodeValue);
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        boolean isNotNull = obj != null;
        if (isNotNull && getClass() == obj.getClass()) {
            ElementPublic other = (ElementPublic) obj;
            boolean tagNameEqual = this.nodeName.equals(other.nodeName);
            boolean listEqual = this.children.equals(other.children);
            boolean attrEqual = this.attributes.equals(other.attributes);
            boolean valueEqual = this.nodeValue.equals(other.nodeValue);
            isEqual = tagNameEqual && listEqual && attrEqual && valueEqual;
        }
        return isEqual;
    }

    @Override
    public String getTagName() {
        return nodeName;
    }

    @Override
    public String getAttribute(String name) {
        String nameAttr = null;
        if(attributes.getNamedItem(name) != null){
            nameAttr = name;
        }
        return nameAttr;
    }

    @Override
    public void setAttribute(String name, String value) {
        if (name != null && value != null) {
            AttrPublic newAttr = new AttrPublic(name, value);
            attributes.setNamedItem(newAttr);
        }
    }

    @Override
    public void removeAttribute(String name) {
        attributes.removeNamedItem(name);
    }

    @Override
    public Attr getAttributeNode(String name) {
        return (Attr) attributes.getNamedItem(name);
    }

    @Override
    public Attr setAttributeNode(Attr newAttr) {
        if (newAttr != null) {
            String name = newAttr.getName();
            String nodeValueAttr = newAttr.getNodeValue();
            if (name != null && nodeValueAttr != null) {
                attributes.setNamedItem(newAttr);
            }
        }
        return newAttr;
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr) {
            AttrPublic attr = null;
        if (oldAttr != null ) {
            attr = (AttrPublic) attributes.removeNamedItem(oldAttr.getName());
        } else {
            oldAttr = null;
        }
        return oldAttr;
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasAttribute(String name) {
        return attributes.getNamedItem(name) != null;
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdAttribute(String name, boolean isId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public short getNodeType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public Node getFirstChild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getLastChild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getPreviousSibling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNextSibling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NamedNodeMap getAttributes() {
        return attributes;
    }

    @Override
    public Document getOwnerDocument() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasChildNodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ElementPublic cloneNode(boolean deep) {
        return this;
    }

    @Override
    public void normalize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSupported(String feature, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNamespaceURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrefix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrefix(String prefix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasAttributes() {
        return attributes.getLength() > 0;
    }

    @Override
    public String getBaseURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public short compareDocumentPosition(Node other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTextContent() {
        //return text.getTextContent();
        return "";
    }

    @Override
    public void setTextContent(String textContent) {
        //text.appendData(textContent);
    }

    @Override
    public boolean isSameNode(Node other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEqualNode(Node arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getFeature(String feature, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getUserData(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
