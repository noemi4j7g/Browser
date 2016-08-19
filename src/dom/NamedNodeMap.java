package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface NamedNodeMap {
    public Node getNamedItem(String name);
    public Node setNamedItem(Node arg);
    public Node removeNamedItem(String name);
    public Node item(int index);
    public int getLength();
    public Node getNamedItemNS(String namespaceURI,String localName);
    public Node setNamedItemNS(Node arg);
    public Node removeNamedItemNS(String namespaceURI,String localName);
}
