package rendering;

import dom_public.TextPublic;
import element_dom.Html;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.MainDocument;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import visitor.BuilderLayoutElementVisitor;
import visitor.CollectorIndexingPropertyVisitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class BuilderLayoutTest {  
        
    @Test
    public void testColorLabelInBody() throws IOException{
        String pathFile = "/docs_tester/index_Specifico.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(0);        
        assertEquals(label.getForeground(), new Color(51, 51, 51));        
    }
    
    @Test
    public void testColorLabelInDiv() throws IOException{
        String pathFile = "/docs_tester/index_Specifico.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(1);  
        assertEquals(label.getForeground(), new Color(255, 123, 224));        
    }
    
    @Test
    public void testColorLabelDivWithClass() throws IOException{
        String pathFile = "/docs_tester/index_Specifico.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(2);        
        assertEquals(label.getForeground(), new Color(0, 0, 224));        
    }
    
    @Test
    public void testFontLabelInDiv() throws IOException{
        String pathFile = "/docs_tester/index_Specifico.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(1);        
        assertEquals(label.getFont(), new Font("sans-serif", Font.PLAIN, 12));        
    }
    
    @Test
    public void testFontSizeLabelDivWithClass() throws IOException{
        String pathFile = "/docs_tester/index_Specifico.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(2);        
        assertEquals(label.getFont(),  new Font("serif", Font.PLAIN, 10));        
    }
    @Test
    public void testColorLabelInDiv2CSS() throws IOException{
        String pathFile = "/docs_tester/index_2CSS.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(0);  
        assertEquals(label.getForeground(), new Color(0, 0, 255));        
    }
    
    @Test
    public void testColorLabelDivWithClass2CSS() throws IOException{
        String pathFile = "/docs_tester/index_2CSS.html";  
        MainDocument mainDocument = new MainDocument(pathFile, false);
        JPanel bFrame;
        bFrame = mainDocument.showPage();   
        JLabel label =(JLabel) bFrame.getComponent(1);    
        assertEquals(label.getForeground(), new Color(0, 0, 255));        
    }
    
    @Test
    public void testMapDataCss() {
        String pathCss = "/docs_tester/style.css";
        BuilderLayout builderLayout = new BuilderLayout();
        Map<String, List<Integer>> expectedMap = new HashMap<>();        
        expectedMap.put("div", Arrays.asList(0));
        expectedMap.put("#idP", Arrays.asList(1));
        expectedMap.put(".classText", Arrays.asList(1));
        expectedMap.put("a", Arrays.asList(2));
        builderLayout.build(pathCss);
        Map<String, List<Integer>> actualMap = builderLayout.getMapDataCss();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void testBuildLayoutWithValidFileCss() {
        String pathCss = "/docs_tester/style.css";
        BuilderLayout builderLayout = new BuilderLayout();
        Map<String, List<Integer>> expectedMap = new HashMap<>();        
        expectedMap.put("div", Arrays.asList(0));
        expectedMap.put("#idP", Arrays.asList(1));
        expectedMap.put(".classText", Arrays.asList(1));
        expectedMap.put("a", Arrays.asList(2));
        builderLayout.build(pathCss);
        Map<String, List<Integer>> actualMap = builderLayout.getMapDataCss();
        assertEquals(expectedMap, actualMap);
        List<LayoutElement> expectedListLayout = new ArrayList<>();
        LayoutElement firstLayoutElement = new LayoutElement();
        firstLayoutElement.setProperty("font-family", "sans-serif");
        firstLayoutElement.setProperty("color", "rgb(255,123,224)");
        expectedListLayout.add(firstLayoutElement);
        LayoutElement secondLayoutElement = new LayoutElement();
        secondLayoutElement.setProperty("color", "rgb(0,0,224)");
        secondLayoutElement.setProperty("background-color", "#FFFF00");
        secondLayoutElement.setProperty("font-size", "10px");
        expectedListLayout.add(secondLayoutElement);
        LayoutElement thirdLayoutElement = new LayoutElement();
        thirdLayoutElement.setProperty("text-decoration", "none");
        expectedListLayout.add(thirdLayoutElement);
        List<LayoutElement> actualListLayout = builderLayout.getListLayoutElement();
        assertEquals(expectedListLayout, actualListLayout);
    }
    
    @Test
    public void testBuildLayoutWithInvalidFileCss() {
        String pathCss = "/docs_tester/invalidFileStyle.css";
        Map<String, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put("body", Arrays.asList(0));
        expectedMap.put("div", Arrays.asList(0));
        BuilderLayout builderLayout = new BuilderLayout();
        builderLayout.build(pathCss);
        Map<String, List<Integer>> actualMap = builderLayout.getMapDataCss();
        assertEquals(expectedMap, actualMap);
        List<LayoutElement> expectedListLayout = new ArrayList<>();
        LayoutElement layoutElement = new LayoutElement();
        layoutElement.setProperty("font-family", "sans-serif");
        expectedListLayout.add(layoutElement);
        List<LayoutElement> actualListLayout = builderLayout.getListLayoutElement();
        assertEquals(expectedListLayout, actualListLayout);
    }
  
    @Test
    public void testBuildLayoutCss() throws IOException {
     
        String pathFile = "/docs_tester/index_Specifico.html";     
     
        MainDocument mainDocument = new MainDocument(pathFile, false);        
        Html html = (Html)mainDocument.document.getDocumentElement();
        BuilderLayoutElementVisitor buildLayElem = new BuilderLayoutElementVisitor();
        html.accept(buildLayElem);
        List<List<LayoutElement>> dataListLayout = buildLayElem.getDataListLayout();        
        assertEquals(dataListLayout.size(),1); 
        List<Map<String, List<Integer>>> listMapDataCss = buildLayElem.getListMapDataCss();  
        CollectorIndexingPropertyVisitor collIndexProp = new CollectorIndexingPropertyVisitor(listMapDataCss);     
        html.accept(collIndexProp);      
        LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapIndexing = collIndexProp.getMapIndexing();
        LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> listMapPrioritiesCss = collIndexProp.getMapPrioritiesCss();      
        assertEquals(mapIndexing.size(),listMapPrioritiesCss.size());

    }
}
