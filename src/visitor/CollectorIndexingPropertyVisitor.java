package visitor;

import dom.Node;
import dom_public.AttrPublic;
import dom_public.NamedNodeMapPublic;
import dom_public.NodeListPublic;
import dom_public.NodePublic;
import dom_public.TextPublic;
import element_dom.A;
import element_dom.Body;
import element_dom.Br;
import element_dom.Div;
import element_dom.H1;
import element_dom.H2;
import element_dom.Head;
import element_dom.Html;
import element_dom.Li;
import element_dom.Link;
import element_dom.P;
import element_dom.Script;
import element_dom.Ul;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author arquitectura de software II-2015
 */
public class CollectorIndexingPropertyVisitor implements Visitor {

    private final LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapIndexing;
    private final LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapElemtPriority;
    private final List<Map<String, List<Integer>>> listMapDataCss;
    private final Set<String> SET_NAME_VALID_ATTR = new HashSet<>(Arrays.asList("id", "class"));
    private List<LinkedHashSet<Integer>> actualList;
    private List<LinkedHashSet<Integer>> priorityList;

    public CollectorIndexingPropertyVisitor(List<Map<String, List<Integer>>> listMapDataCss) {
        this.listMapDataCss = listMapDataCss;
        mapIndexing = new LinkedHashMap<>();
        actualList = new ArrayList<>();
        priorityList = new ArrayList<>();
        mapElemtPriority = new LinkedHashMap<>();
    }

    @Override
    public void visit(Html html) {
        NodeListPublic listNode = (NodeListPublic) html.getChildNodes();
        listNode.setNodeName("#html");
        visitChildNodes(listNode);
    }

    @Override
    public void visit(Head head) {
        NodeListPublic listNode = (NodeListPublic) head.getChildNodes();
        listNode.setNodeName("#head");
        visitChildNodes(listNode);
    }

    @Override
    public void visit(H1 h1) {
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        indexData(h1.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) h1.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) h1.getChildNodes();
        listNode.setNodeName("#h1");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList=listNoEditPriotity;
    }

    @Override
    public void visit(H2 h2) {
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        indexData(h2.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) h2.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) h2.getChildNodes();
        listNode.setNodeName("#h2");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList = listNoEditPriotity;
    }

    @Override
    public void visit(Link link) {
    }

    @Override
    public void visit(Script script) {
    }

    @Override
    public void visit(Body body) {
        indexData(body.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) body.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) body.getChildNodes();
        listNode.setNodeName("#body");
        visitChildNodes(listNode);
    }

    @Override
    public void visit(A a) {
        List<LinkedHashSet<Integer>> listNoEdit = new ArrayList<>(actualList);
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        indexData(a.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) a.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) a.getChildNodes();
        listNode.setNodeName("#a");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList = listNoEditPriotity;
    }

    @Override
    public void visit(P p) {
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        indexData(p.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) p.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) p.getChildNodes();
        listNode.setNodeName("#p");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList = listNoEditPriotity;
    }

    @Override
    public void visit(Div div) {
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        indexData(div.getNodeName());        
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) div.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) div.getChildNodes();
        listNode.setNodeName("#div");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList = listNoEditPriotity;
    }

    @Override
    public void visit(TextPublic textDom) {     
        mapIndexing.put(textDom, cloneList(actualList));
        mapElemtPriority.put(textDom, cloneList(priorityList));
    }

    @Override
    public void visit(AttrPublic attribute) {
        String nameAttribute = attribute.getName();
        if (SET_NAME_VALID_ATTR.contains(attribute.getName())) {
            String value = attribute.getValue();
            switch(nameAttribute){
                case "id":
                    value = "#" + value;
                    break;
                case "class":
                    value = "." + value;
                    break;
                default:
                    break;
            }
            indexData(value);
        }
    }

    @Override
    public void visit(Br br) {
    }

    @Override
    public void visit(Ul ul) {
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        indexData(ul.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) ul.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) ul.getChildNodes();
        listNode.setNodeName("#ul");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList=listNoEditPriotity;
    }

    @Override
    public void visit(Li li) {
        List<LinkedHashSet<Integer>> listNoEditPriotity = cloneList(priorityList);
        List<LinkedHashSet<Integer>> listNoEdit = cloneList(actualList);
        indexData(li.getNodeName());
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) li.getAttributes();
        visitAttributes(namedNodeMap);
        NodeListPublic listNode = (NodeListPublic) li.getChildNodes();
        listNode.setNodeName("#li");
        visitChildNodes(listNode);
        actualList = listNoEdit;
        priorityList=listNoEditPriotity;
    }

    private void visitAttributes(NamedNodeMapPublic namedNodeMap) {
        for (String nameAttr : namedNodeMap) {
            ((AttrPublic) namedNodeMap.getNamedItem(nameAttr)).accept(this);
        }
    }

    private void visitChildNodes(NodeListPublic listNode) {
        for (Node node : listNode) {
            ((NodePublic) node).accept(this);
        }
    }

    private void indexData(String datum) {        
        int indexList = 0;
        int size;       
       
        if (datum.contains(".")|| datum.contains("#")){          
          datum=datum.replace( datum.substring(1, 2), "");      
            
        }        
        for (Map<String, List<Integer>> mapDataCss : listMapDataCss) {
            size = actualList.size();           
            if (mapDataCss.containsKey(datum)) {             
                List<Integer> listIndex = mapDataCss.get(datum);  
                List<Integer> listPriotity = Arrays.asList(GetProrityCss(datum));
                if (size == indexList) {
                    actualList.add(new LinkedHashSet<>());
                    priorityList.add(new LinkedHashSet<>());
                }              
                actualList.get(indexList).addAll(listIndex);
                priorityList.get(indexList).addAll(listPriotity);               
            }
           // indexList += 1;
        }  
      indexList = 0;
    }
    private int GetProrityCss(String datum){
        int priority;
        priority = 1;
        if (datum.contains("body"))  priority = 0;
        if (datum.contains("."))    priority = 3;         
        if (datum.contains("#"))   priority = 4;
      
        return priority;
    }
            

    public LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> getMapIndexing() {
        return mapIndexing;
    }
    
    public LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> getMapPrioritiesCss() {
        return mapElemtPriority;
    }
    
    private List<LinkedHashSet<Integer>> cloneList(List<LinkedHashSet<Integer>> list) {
        List<LinkedHashSet<Integer>> clone ;
        clone = new ArrayList<>();
        list.stream().forEach((item) -> {
            clone.add(item);
        });          
        return clone;      
    }
}
