package parser;

import dom.DOMException;
import dom_public.NodePublic;
import dom_public.TextPublic;
import element_dom.A;
import element_dom.Li;
import element_dom.Ul;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tokenizer.Tokenizer;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserListTest {
    
    @Test
    public void testParseUlWithAttribute() {
        String nameAttr = "name";
        String valueAttr = "\"list\"";
        String text = String.format("<ul %s=%s></ul>", nameAttr, valueAttr);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserList parserList = new ParserList(reviewTok);
        Ul ul = new Ul();
        ul.setAttribute(nameAttr, valueAttr);
        List<NodePublic> expectedList = Arrays.asList(ul);
        List<NodePublic> actualList = parserList.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testParseUlWithElementList() {
        String firstTextLi = "First element";
        String firstSubTextLi = "First sub-element";
        String secondSubTextLi = "First element";
        String secondTextLi = "Second element";
        String firstTextA = "Link 1";
        String text = String.format("<ul><li>%s<ul><li>%s</li><li><a>%s</a></li></ul></li><li>%s<a>%s</a></li></ul><ul></ul>", firstTextLi, firstSubTextLi, secondSubTextLi, secondTextLi, firstTextA);
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserList parserList = new ParserList(reviewTok);     
        Ul firstUl = new Ul();
        Li firstLi = new Li();
        firstLi.appendChild(new TextPublic(firstTextLi));
        Ul subUl = new Ul();
        Li firstSubLi = new Li();
        firstSubLi.appendChild(new TextPublic(firstSubTextLi));
        Li secondSubLi = new Li();
        A firstA = new A();
        firstA.appendChild(new TextPublic(secondSubTextLi));
        secondSubLi.appendChild(firstA);
        subUl.appendChild(firstSubLi);
        subUl.appendChild(secondSubLi);
        firstLi.appendChild(subUl);
        firstUl.appendChild(firstLi);
        Li secondLi = new Li();
        secondLi.appendChild(new TextPublic(secondTextLi));
        A secondA = new A();
        secondA.appendChild(new TextPublic(firstTextA));
        secondLi.appendChild(secondA);
        firstUl.appendChild(secondLi);
        Ul secondUl = new Ul();

        List<NodePublic> expectedList = Arrays.asList(firstUl, secondUl);
        List<NodePublic> actualList = parserList.parse();
        assertEquals(expectedList, actualList);
    }
    
    @Test (expected = DOMException.class)
    public void testParseListWithInvalidData() {
        String text = "<ul>Text of ul<li></li></ul>";
        Tokenizer tokenizer = new Tokenizer(text);
        ReviewTokenizer reviewTok = new ReviewTokenizer(tokenizer);
        ParserList parserList = new ParserList(reviewTok);
        parserList.parse();
    }
    
}
