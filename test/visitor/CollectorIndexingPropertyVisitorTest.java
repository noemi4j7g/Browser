package visitor;

import dom.Text;
import dom_public.TextPublic;
import element_dom.Body;
import element_dom.Div;
import element_dom.P;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author arquitectura de software II-2015
 */
public class CollectorIndexingPropertyVisitorTest {
    
    @Test
    public void testGetListIndexCss() {
        Body body = new Body();
        body.setAttribute("class", "classElement");
        Div div = new Div();
        TextPublic textDiv = new TextPublic("This is the text of div");
        div.appendChild(textDiv);
        body.appendChild(div);
        P p = new P();
        p.setAttribute("id", "idP");
        TextPublic textP = new TextPublic("This is the text of p");
        p.appendChild(textP);
        body.appendChild(p);
        List<Map<String, List<Integer>>> listMapDataCss = new ArrayList<>();
        Map<String, List<Integer>> firstMapDataCss = new HashMap<>();
        firstMapDataCss.put("body", Arrays.asList(0,1));
        firstMapDataCss.put("div", Arrays.asList(1,2));
        firstMapDataCss.put("#idP", Arrays.asList(3));
        listMapDataCss.add(firstMapDataCss);
        Map<String, List<Integer>> secondMapDataCss = new HashMap<>();
        secondMapDataCss.put("body", Arrays.asList(1,3));
        secondMapDataCss.put("div", Arrays.asList(4));
        secondMapDataCss.put(".classElement", Arrays.asList(0));
        listMapDataCss.add(secondMapDataCss);
        
        CollectorIndexingPropertyVisitor collecIndexPropVisitor = new CollectorIndexingPropertyVisitor(listMapDataCss);
        body.accept(collecIndexPropVisitor);
        
        LinkedHashMap<Text, List<LinkedHashSet<Integer>>> expectedMapIndexing = new LinkedHashMap<>();
        List<LinkedHashSet<Integer>> listLinkHashSetDiv = new ArrayList<>();
        LinkedHashSet<Integer> firstLinkHashSetDiv = new LinkedHashSet<>();
        firstLinkHashSetDiv.add(0);
        firstLinkHashSetDiv.add(1);
        firstLinkHashSetDiv.add(2);
        listLinkHashSetDiv.add(firstLinkHashSetDiv);
        LinkedHashSet<Integer> secondLinkHashSetDiv = new LinkedHashSet<>();
        secondLinkHashSetDiv.add(1);
        secondLinkHashSetDiv.add(3);
        secondLinkHashSetDiv.add(0);
        secondLinkHashSetDiv.add(4);
        listLinkHashSetDiv.add(secondLinkHashSetDiv);
        expectedMapIndexing.put(textDiv, listLinkHashSetDiv);
        List<LinkedHashSet<Integer>> listLinkHashSetP = new ArrayList<>();
        LinkedHashSet<Integer> firstLinkHashSetP = new LinkedHashSet<>();
        firstLinkHashSetP.add(0);
        firstLinkHashSetP.add(1);
        firstLinkHashSetP.add(3);
        listLinkHashSetP.add(firstLinkHashSetP);
        LinkedHashSet<Integer> secondLinkHashSetP = new LinkedHashSet<>();
        secondLinkHashSetP.add(1);
        secondLinkHashSetP.add(3);
        secondLinkHashSetP.add(0);
        listLinkHashSetP.add(secondLinkHashSetP);
        expectedMapIndexing.put(textP, listLinkHashSetP);
        LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> actualMapIndexing = collecIndexPropVisitor.getMapIndexing();
        assertEquals(expectedMapIndexing, actualMapIndexing);
    }

}
