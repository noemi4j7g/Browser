package main;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ReaderFile {

    private final StringBuilder textFile;
    private final List<String> listText;
    private final boolean externalProject;
  
    public ReaderFile(String pathFile, boolean externalProject) throws IOException {
        textFile = new StringBuilder();
        listText = new ArrayList<>();
        this.externalProject = externalProject;          
        getTextFile(pathFile);
       
    }

    public String getTextFile() {
        return textFile.toString().trim();
    }

    public List<String> getListText() {
        return listText;
    }

    private void getTextFile(String pathFile) throws FileNotFoundException, IOException {
        String msnError = "";
        BufferedReader bufRead = null;       
        try {
            if (externalProject) {
                FileReader fileReader = new FileReader(pathFile);
                bufRead = new BufferedReader(fileReader);
            } else {                
                pathFile=setPath(pathFile);                    
                InputStream ipStrm = ReaderFile.class.getResourceAsStream(pathFile);
                InputStreamReader ipStrmRdr = new InputStreamReader(ipStrm);
                bufRead = new BufferedReader(ipStrmRdr);
                
            }
            String line;
            while ((line = bufRead.readLine()) != null) {
                if (!line.equals("")) {
                    textFile.append(" ");
                    textFile.append(line);
                    listText.add(line);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("Error, path of file not found: " + fileNotFoundException);
        } catch (IOException ioException) {
            throw new IOException("Error to try read the file: " + ioException);
        } catch (NullPointerException nullPointExcept) {
            throw new NullPointerException(String.format("Error, the path %s is not valid. %s", pathFile, nullPointExcept));
        }          
    }
            
    private String setPath(String pathFile){  
           
        if (!pathFile.contains("docs") && !"".equals(pathFile)){ 
           pathFile="/docs/"+ pathFile;        
        }            
        return pathFile;
    }    
 
}
