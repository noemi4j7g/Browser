
package main;


import gui.MsgBox;
/**
 *
 * @author Noemi Guzman
 */
public class URLPath {

    private String msnError;

    public String setPath(String pathFile) {

        if (!pathFile.contains("docs") && !"".equals(pathFile)) {
            pathFile = "/docs/" + pathFile;
        }
        return pathFile;
    }

    public boolean isPathValid(String pathFile) {     
       return (pathFile != null && !(pathFile.isEmpty())
                && pathFile.contains(".html"));
        
    }

    public void displayErrorMsn(String pathFile) {
        String errorMensage = "";
        if (pathFile == null || (pathFile.isEmpty())) {
            errorMensage = "Error, Enter a valid html file e.g. index.html";            
        }
        if (pathFile.contains(".html")==false) {
            errorMensage = "Error, Enter a html file name e.g. index.html";            
        }       
        msnError = String.format(errorMensage);
        MsgBox.error(msnError, "html path");
    }

}
