package dom_public;

import dom.NamedNodeMap;
import dom.Node;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public class NamedNodeMapPublic implements NamedNodeMap, Iterable<String>{

    private final Map<String, Node> namedNodeMap;

    public NamedNodeMapPublic() {
        namedNodeMap = new LinkedHashMap<>();
    }

    @Override
    public Node getNamedItem(String name) {
        return namedNodeMap.get(name);
    }

    @Override
    public Node setNamedItem(Node node) {
        Node newNode = null;
        String name = node.getNodeName();
        if (name != null) {
            newNode = namedNodeMap.put(name, node);
        }
        return newNode;
    }

    @Override
    public Node removeNamedItem(String name) {
        return namedNodeMap.remove(name);
    }

    @Override
    public Node item(int index) {
        Node node = null;
        if (index >= 0 && index < getLength()) {
            Object[] array = namedNodeMap.keySet().toArray();
            String name = (String) array[index];
            node = namedNodeMap.get(name);
        }
        return node;
    }

    @Override
    public int getLength() {
        return namedNodeMap.size();
    }

    @Override
    public Node getNamedItemNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node setNamedItemNS(Node arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node removeNamedItemNS(String namespaceURI, String localName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.namedNodeMap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            final NamedNodeMapPublic other = (NamedNodeMapPublic) obj;
            isEqual = this.namedNodeMap.equals(other.namedNodeMap);
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "NamedNodeMapPublic{" + "namedNodeMap=" + namedNodeMap + '}';
    }

    @Override
    public Iterator<String> iterator() {
        return namedNodeMap.keySet().iterator();
    }

}
