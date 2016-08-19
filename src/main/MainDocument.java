package main;

import dom.DOMException;
import dom_public.DocumentPublic;
import dom_public.TextPublic;
import element_dom.Html;
import gui.RenderedPage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import parser.Parser;
import parser.ParserHtml;
import parser.ReviewTokenizer;
import rendering.LayoutElement;
import tokenizer.Tokenizer;
import visitor.BuilderLayoutElementVisitor;
import visitor.BuilderTextVisitor;
import visitor.CollectorIndexingPropertyVisitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class MainDocument {

    public DocumentPublic document;

    public MainDocument(String pathFile, boolean externalFile) throws IOException {
        initialize(pathFile, externalFile);
    }

    private void initialize(String pathFile, boolean externalFile) throws IOException {
        try {
            ReaderFile readerFile = new ReaderFile(pathFile, externalFile);
            String text = readerFile.getTextFile();              
            Tokenizer tokenizer = new Tokenizer(text);
            ReviewTokenizer reviewTokenizer = new ReviewTokenizer(tokenizer);
            Parser parserHtml = new ParserHtml(reviewTokenizer);
            Html html = (Html) parserHtml.parse();
            document = new DocumentPublic(html);
            
        } catch (DOMException domException) {
            document = null;
            throw domException;
        } catch (IOException ioException){
            throw ioException;
        }
    }

    public String getContentDocument() {
        BuilderTextVisitor visitor = new BuilderTextVisitor();
        Html html = (Html)document.getDocumentElement();
        html.accept(visitor);
        return visitor.getText();
    }

    public JPanel showPage(){
        Html html = (Html)document.getDocumentElement();
        BuilderLayoutElementVisitor buildLayElem = new BuilderLayoutElementVisitor();
        html.accept(buildLayElem);
        List<List<LayoutElement>> dataListLayout = buildLayElem.getDataListLayout();      
        List<Map<String, List<Integer>>> listMapDataCss = buildLayElem.getListMapDataCss();       
        CollectorIndexingPropertyVisitor collIndexProp = new CollectorIndexingPropertyVisitor(listMapDataCss);          
        html.accept(collIndexProp);        
        LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapIndexing = collIndexProp.getMapIndexing();
        LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> listMapPrioritiesCss =collIndexProp.getMapPrioritiesCss();      
        RenderedPage renderedPage = new RenderedPage(dataListLayout, mapIndexing,listMapPrioritiesCss);
        return renderedPage.createHTMLpage();
    }
   
    public DocumentPublic getDocument() {
        return document;
    }
}
