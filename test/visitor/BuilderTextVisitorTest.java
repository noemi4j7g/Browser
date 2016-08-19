package visitor;

import dom_public.AttrPublic;
import dom_public.TextPublic;
import element_dom.Body;
import element_dom.Head;
import element_dom.Html;
import element_dom.Link;
import element_dom.Script;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author arquitectura de software II-2015
 */
public class BuilderTextVisitorTest {
    
    @Test
    public void testTesterVisitorHtml(){
        BuilderTextVisitor builderTextVisitor = new BuilderTextVisitor();
        Html html = new Html();
        html.accept(builderTextVisitor);
        String expectedString = "<html></html>";
        String actualString = builderTextVisitor.getText();
        assertEquals(expectedString, actualString);
    }
    
    @Test
    public void testDocumentWithAttribute(){
        BuilderTextVisitor builderTextVisitor = new BuilderTextVisitor();
        Html html = new Html();
        html.setAttribute("lang", "\"es\"");
        html.accept(builderTextVisitor);
        String expectedString = "<html lang=\"es\"></html>";
        String actualString = builderTextVisitor.getText();
        assertEquals(expectedString, actualString);
    }
    
    @Test
    public void testDocumentWithText(){
        BuilderTextVisitor buildetTextVisitor = new BuilderTextVisitor();
        Body body = new Body();
        String text = "This is the text of body";
        body.appendChild(new TextPublic(text));
        body.accept(buildetTextVisitor);
        String expectedString = "<body>" + text + "</body>";
        String actualString = buildetTextVisitor.getText();
        assertEquals(expectedString, actualString);
    }
    
    @Test
    public void testDocumentWithManyElements(){
        BuilderTextVisitor buildetTextVisitor = new BuilderTextVisitor();
        Head head = new Head();
        Link link = new Link();
        Script script = new Script();
        String nameAttr = "src";
        String valueAttr = "\"doc.js\"";
        script.setAttributeNode(new AttrPublic(nameAttr, valueAttr));
        head.appendChild(link);
        head.appendChild(script);
        head.accept(buildetTextVisitor);
        String expectedString = String.format("<head><link/><script %s=%s></script></head>", nameAttr, valueAttr);
        String actualString = buildetTextVisitor.getText();
        assertEquals(expectedString, actualString);
    }
}