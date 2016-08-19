package visitor;

import dom_public.TextPublic;
import element_dom.A;
import element_dom.Body;
import element_dom.H1;
import element_dom.H2;
import element_dom.Head;
import element_dom.Html;
import element_dom.Link;
import element_dom.P;
import element_dom.Script;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ListCollectorTextVisitorTest {
    
    @Test
    public void testVisitHtml() {
        Html html = new Html();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        html.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitHead() {
        Head head = new Head();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        head.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitH1() {
        H1 h1 = new H1();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        h1.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitH2() {
        H2 h2 = new H2();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        h2.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitLink() {
        Link link = new Link();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        link.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitScript() {
        Script script = new Script();
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        script.accept(listCollTextVis);
        List<TextPublic> expectedList = new ArrayList<>();
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testVisitBody() {
        //for <body><p>Text of P<a>Link of 'A'</a>Text of Body</body>
        Body body = new Body();
        
        String textP = "Text of P";
        TextPublic textDomP = new TextPublic(textP);
        P p = new P();
        p.appendChild(textDomP);
        
        String textA = "Link of 'A'";
        TextPublic textDomA = new TextPublic(textA);
        A a = new A();
        a.appendChild(textDomA);
        p.appendChild(a);
        body.appendChild(p);
        
        String textBody = "Text of Body";
        TextPublic textDomBody = new TextPublic(textBody);
        body.appendChild(textDomBody);
        
        ListCollectorTextVisitor listCollTextVis = new ListCollectorTextVisitor();
        body.accept(listCollTextVis);
        List<TextPublic> expectedList = Arrays.asList(textDomP, textDomA, textDomBody);
        List<TextPublic> actualList = listCollTextVis.getListText();
        assertEquals(expectedList, actualList);
    }
    
}
