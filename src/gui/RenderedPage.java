package gui;

import dom_public.TextPublic;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rendering.LayoutElement;

/**
 *
 * @author arquitectura de software II-2015
 */
public class RenderedPage {

    private final List<List<LayoutElement>> dataListLayout;
    private final LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapIndexing;
    private final LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapPrioritiesCss;
    private final JPanel panel;
    
    private int posY;
    private int priorityCss;


    public RenderedPage(List<List<LayoutElement>> dataListLayout, LinkedHashMap<TextPublic,
            List<LinkedHashSet<Integer>>> mapIndexing, LinkedHashMap<TextPublic, List<LinkedHashSet<Integer>>> mapPrioritiesCss) {
        this.dataListLayout = dataListLayout;
        this.mapIndexing = mapIndexing;
        this.mapPrioritiesCss = mapPrioritiesCss;
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBounds(0, 40, 600, 600);
        posY = 210;
    }
    
    public JPanel createHTMLpage() {
        return createAndShowGUI();
    }

    private JPanel createAndShowGUI() {     
        panel.setBackground(Color.white);
        panel.setForeground(Color.black);             
        setText();        
        return panel;
    }

    private void setText() {                  
        mapIndexing.keySet().stream().forEach((textPublic) -> {
            getStyleOfTextPublic(textPublic);
        });                               
    }
    
    private void getStyleOfTextPublic(TextPublic textPublic){
        int indexDataListLayout=0;               
        priorityCss=0;      
      
        JLabel labelText = new JLabel( textPublic.getNodeValue());      
        List<LinkedHashSet<Integer>> listLinkHashSet = mapIndexing.get(textPublic);
              
        for (LinkedHashSet<Integer> linkHashSet : listLinkHashSet) {            
            labelText = createLabelFromtextPublic(linkHashSet,textPublic,indexDataListLayout,labelText);            
            indexDataListLayout += 1;                
        }    
        addLabelToPanel(labelText);                       
    }
    
    private JLabel createLabelFromtextPublic(LinkedHashSet<Integer> linkHashSet,TextPublic textPublic, 
                                                int indexDataListLayout,JLabel labelText){
        LayoutElement layoutElem;
        int indexCss=0;                
                  
        for (int n : linkHashSet) {                      
            layoutElem = dataListLayout.get(indexDataListLayout).get(n);
            int prioCss=getPriorityCss(textPublic,indexDataListLayout,indexCss);                 
            labelText = getLabelWithProperty(layoutElem, labelText,prioCss);  
            indexCss+=1;
        }
        return labelText;
    }
    
    private int getPriorityCss(TextPublic textPublic,int indexDataListLayout, int n) {
        List<LinkedHashSet<Integer>> listPrioritiesCss = mapPrioritiesCss.get(textPublic);
        LinkedHashSet<Integer> layoutPriorityCss = listPrioritiesCss.get(indexDataListLayout); 
        int prioCss=0;
        int index = 0;  
        for (int nCSS : layoutPriorityCss) { 
            if (index==n){
                return nCSS;
            }
            index +=1;
        }
        return prioCss;         
    }
            
    private void addLabelToPanel(JLabel label){
        label.setBounds(0, posY, 600, 15);   
        panel.add(label);
        posY += 30;                        
        panel.revalidate();
        panel.repaint(); 
    }
    
    private JLabel getLabelWithProperty(LayoutElement layoutElem,JLabel label,int priorityListLayout) {
        
        if (priorityListLayout>=priorityCss || priorityCss==0 || priorityListLayout==1){  
           
            Color backColor = layoutElem.getBackgroundColor();       
            if (backColor != null) {     
                label.setBackground(backColor);                
            }
            Color colorText = layoutElem.getColorText();
            if (colorText != null) {      
                label.setForeground(colorText);          
            }
            Font font = layoutElem.getFont();
            if (font != null) {
                label.setFont(font);
            }           
            priorityCss=priorityListLayout;
        }        
        return label;
    }       
}
