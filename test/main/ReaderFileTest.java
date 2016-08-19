package main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ReaderFileTest {
    
     @Test (expected = NullPointerException.class)
    public void testReaderFileWithNoExistentFile() throws IOException {
        String pathFile = "/docs/FileOne.html";
        ReaderFile readerFile = new ReaderFile(pathFile, false);
    }
    
    @Test
    public void testReaderFileGetTextFile() {
       try {
           String pathFile = "/docs/TestDoc.txt";
           ReaderFile readerFile = new ReaderFile(pathFile, false);
           String expectedString = "This text is to test the class ReaderFile.";
           String actualString = readerFile.getTextFile();
           assertEquals(expectedString, actualString);
           
           List<String> expectedListText = Arrays.asList(expectedString);
           List<String> actualListText = readerFile.getListText();
           assertEquals(expectedListText, actualListText);
       } catch (IOException ex) {
           assertFalse(true);
       }
    }
    
}
