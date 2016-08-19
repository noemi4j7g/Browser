package dom_public;


//import org.w3c.dom.*;

import dom.Attr;
import dom.Document;
import dom.Element;
import dom.NamedNodeMap;
import dom.Node;
import static dom.Node.ATTRIBUTE_NODE;
import dom.NodeList;
import dom.TypeInfo;
import dom.UserDataHandler;
import java.util.Objects;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */

public class AttrPublic implements Attr, NodePublic{

    private final String nodeName;
    private String nodeValue;
    
    public AttrPublic(String nodeName, String nodeValue) {
        this.nodeName = nodeName;
        this.nodeValue = nodeValue;
    }

    @Override
    public String getName() {
        return nodeName;
    }

    @Override
    public String getValue() {
        return nodeValue;
    }

    @Override
    public void setValue(String newNodeValue) {
        nodeValue = newNodeValue;
    }

    @Override
    public Node appendChild(Node newChild) {
        return newChild;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getNodeValue() {
        return getValue();
    }

    @Override
    public void setNodeValue(String newValue) {
        setValue(newValue);
    }

    @Override
    public NodeList getChildNodes() {
        return null;
    }

    @Override
    public Node removeChild(Node oldChild) {
        return null;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nodeName);
        hash = 83 * hash + Objects.hashCode(this.nodeValue);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        boolean isNull = object == null;
        if(!isNull && object instanceof AttrPublic){
            AttrPublic other = (AttrPublic) object;
            boolean idEqual = this.nodeName.equals(other.nodeName);
            boolean valueEqual = this.nodeValue.equals(other.nodeValue);
            isEqual = idEqual && valueEqual;
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Atributo{" + "nodeName=" + nodeName + ", nodeValue=" + nodeValue + '}';
    }

    @Override
    public boolean getSpecified() {
        return false;
    }

    @Override
    public Element getOwnerElement() {
        return null;
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isId() {
        return nodeName.equals("id");
    }

    @Override
    public short getNodeType() {
        return ATTRIBUTE_NODE;
    }

    @Override
    public Node getParentNode() {
        return this;
    }

    @Override
    public Node getFirstChild() {
        return this;
    }

    @Override
    public Node getLastChild() {
        return this;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Node cloneNode(boolean deep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTextContent(String textContent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
