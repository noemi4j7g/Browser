package dom_public;

//import org.w3c.dom.*;

import dom.Node;
import dom.NodeList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */

public class NodeListPublic implements NodeList, Iterable<Node>{
    private final List<Node> list;
    private String nodeName;
    
    public NodeListPublic(){
        list = new ArrayList<>();
        nodeName = "#documment";
    }
    
    @Override
    public int getLength() {
        return list.size();
    }
     
    public void setNodeName(String nodeName){
        this.nodeName = nodeName;
    }
    
    public String getNodeName(String nodeName){
        return this.nodeName;
    }
    
    @Override
    public Node item(int index) {
        Node node = null;
        int sizeList = getLength();
        if(index >= 0 && index < sizeList){
            node = list.get(index);
        }
        return node;
    }
    
    public void add(Node node){
        if(node != null){
            list.add(node);
        }
    }
    
    public Node remove(Node oldChild){
        Node nodeRemoved = list.remove(0);
        return nodeRemoved;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.list);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean listEqual = false;
        boolean isNull = object == null;
        if(!isNull && getClass() == object.getClass()){
            NodeListPublic other = (NodeListPublic) object;
            listEqual = this.list.equals(other.list);
        }
        return listEqual;
    }  

    @Override
    public String toString() {
        return "ListNode: " + list;
    }

    @Override
    public Iterator<Node> iterator() {
        return list.iterator();
    }
    
    public int indexOf(Node node){
        return list.indexOf(node);
    }
    
}
