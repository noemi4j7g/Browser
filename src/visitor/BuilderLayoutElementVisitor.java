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
import java.util.List;
import java.util.Map;
import rendering.BuilderLayout;
import rendering.LayoutElement;

/**
 *
 * @author arquitectura de software II-2015
 */
public class BuilderLayoutElementVisitor implements Visitor{

    private final List<Map<String, List<Integer>>> listMapDataCss;
    private final List<Map<String, List<Integer>>> listMapPriorityCss;
    private final List<List<LayoutElement>> dataListLayout;
    private final BuilderLayout builderLayout;
    
    public BuilderLayoutElementVisitor() {
        listMapDataCss = new ArrayList<>();
        listMapPriorityCss = new ArrayList<>();
        dataListLayout = new ArrayList<>();
        builderLayout = new BuilderLayout();
    }
    
    @Override
    public void visit(Html html) {
        NodeListPublic listNode = (NodeListPublic) html.getChildNodes();
        visitChildNodes(listNode);
    }

    @Override
    public void visit(Head head) {
 
        NodeListPublic listNode = (NodeListPublic) head.getChildNodes();
        visitChildNodes(listNode);
        setCssDefault();
    }

    @Override
    public void visit(H1 h1) {
    }

    @Override
    public void visit(H2 h2) {
    }

    @Override
    public void visit(Link link) {
        NamedNodeMapPublic namedNodeMap = (NamedNodeMapPublic) link.getAttributes();
        visitAttributes(namedNodeMap);
    }

    @Override
    public void visit(Script script) {
    }

    @Override
    public void visit(Body body) {
    }

    @Override
    public void visit(A a) {
    }

    @Override
    public void visit(P p) {
    }

    @Override
    public void visit(Div div) {
    }

    @Override
    public void visit(TextPublic textDom) {
    }

    @Override
    public void visit(AttrPublic attribute) {
        String nameAttr = attribute.getName();      
        if(nameAttr.equals("href")){
            String pathCss = attribute.getValue();           
            pathCss = pathCss.replaceAll("\"", "");
            builderLayout.build(pathCss);
            Map<String, List<Integer>> mapBuilder = builderLayout.getMapDataCss();          
            if(!mapBuilder.isEmpty()){              
                listMapDataCss.add(mapBuilder);                    
                dataListLayout.add(builderLayout.getListLayoutElement());
                
            }   
            
        }
  
    }
    public void setCssDefault() {      
        if (listMapDataCss.isEmpty()){       
            String pathCss = "/docs/default.css";           
            pathCss = pathCss.replaceAll("\"", "");
            builderLayout.build(pathCss);
            Map<String, List<Integer>> mapBuilder = builderLayout.getMapDataCss();          
            if(!mapBuilder.isEmpty()){              
                listMapDataCss.add(mapBuilder);              
                dataListLayout.add(builderLayout.getListLayoutElement());
            }
        
        } 
    }
    
            
    @Override
    public void visit(Br br) {
    }

    @Override
    public void visit(Ul ul) {
    }

    @Override
    public void visit(Li li) {
    }
    
    private void visitChildNodes(NodeListPublic listNode) {
        for (Node node : listNode) {
            ((NodePublic) node).accept(this);
        }
    }

    public List<Map<String, List<Integer>>> getListMapDataCss() {
        //
    
        return listMapDataCss;
    }

    public List<List<LayoutElement>> getDataListLayout() {
        return dataListLayout;
    }

    private void visitAttributes(NamedNodeMapPublic namedNodeMap) {
        for (String nameAttr : namedNodeMap) {
            ((AttrPublic) namedNodeMap.getNamedItem(nameAttr)).accept(this);
        }
    }

    public List<Map<String, List<Integer>>> getMapPrioritiesCss() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
