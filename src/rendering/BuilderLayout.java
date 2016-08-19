package rendering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.ReaderFile;

/**
 *
 * @author arquitectura de software II-2015
 */
public class BuilderLayout {
    
    private Map<String, List<Integer>> mapDataCss;
    private List<LayoutElement> listLayoutElement;
    private int indexList;

    public BuilderLayout() {
        mapDataCss = null;
        listLayoutElement = null;
        indexList = 0;
    }

    public void build(String pathCss) {
        indexList = 0;
        mapDataCss = new HashMap<>();
        listLayoutElement = new ArrayList<>();
        try {
           
            ReaderFile readerFile = new ReaderFile(pathCss, false);
            String textFile = readerFile.getTextFile();
            textFile = textFile.replaceAll("\\s+", " ").trim();
            parseTextFileCss(textFile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Map<String, List<Integer>> getMapDataCss() {
        return mapDataCss;
    }

    public List<LayoutElement> getListLayoutElement() {
        return listLayoutElement;
    }

    private void parseTextFileCss(String textFile) {
        int indexEnd, indexCloseAngleBracket;
        String headerLayout, bodyLayout;
       
        while((indexEnd = textFile.indexOf("{")) > 0){
            
            headerLayout = textFile.substring(0, indexEnd).trim();
    
            indexCloseAngleBracket = textFile.indexOf("}");
        
            if(indexCloseAngleBracket > indexEnd){
                bodyLayout = textFile.substring(indexEnd + 1, indexCloseAngleBracket).trim();
           
                buildLayout(headerLayout, bodyLayout);
                textFile = textFile.substring(indexCloseAngleBracket + 1);
            }else{
                break;
            }
        }
    }

    private void buildLayout(String headerLayout, String bodyLayout) {
        String[] identifiers = headerLayout.split(",");
      
        List<String> assigningProperties = Arrays.asList(bodyLayout.split(";"));
        LayoutElement layoutElement = new LayoutElement();
        String namePropertie, valuePropertie;
        for(String assigning: assigningProperties){
            String[] properties = assigning.split(":");
            if(properties.length == 2){
                namePropertie = properties[0];
                valuePropertie = properties[1];
                layoutElement.setProperty(namePropertie, valuePropertie);
            }
        }
        if(layoutElement.hasAssignedProperty()){
            listLayoutElement.add(layoutElement);
            registerData(identifiers);
        }
    }

    private void registerData(String[] identifiers) {
        List<String> listIdentifier = Arrays.asList(identifiers);
        for(String identifier: listIdentifier){
            identifier = identifier.trim();
            if(mapDataCss.containsKey(identifier)){
                mapDataCss.get(identifier).add(indexList);
            }else{
                mapDataCss.put(identifier, Arrays.asList(indexList));
            }
        }
        indexList += 1;
    }
    
}
