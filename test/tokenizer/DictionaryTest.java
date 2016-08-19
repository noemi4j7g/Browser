package tokenizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author arquitectura de software II-2015
 */
public class DictionaryTest {

    @Test
    public void testRegisterDataWithValidPath() {
        String pathFile = "/docs/DataDictionary.txt";
        try {
            Dictionary dictionary = new Dictionary(pathFile);
            assertTrue(true);
        } catch (IOException ioException) {
            assertFalse(false);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testRegisterDataWithNoExistentPath() throws IOException {
        String pathFile = "/docs/Data.txt";
        Dictionary dictionary = new Dictionary(pathFile);
    }

    @Test
    public void testVerifyExistentNameTag() {
        String pathFile = "/docs/DataDictionary.txt";
        Dictionary dictionary;
        try {
            dictionary = new Dictionary(pathFile);
            String nameTag = "div";
            assertTrue(dictionary.existNameTag(nameTag));
        } catch (IOException ex) {
            assertFalse(false);
        }
    }

    @Test
    public void testVerifyNoExistentNameTag() {
        String pathFile = "/docs/DataDictionary.txt";
        Dictionary dictionary;
        try {
            dictionary = new Dictionary(pathFile);
            String nameTag = "tag";
            assertFalse(dictionary.existNameTag(nameTag));
        } catch (IOException ex) {
            assertFalse(false);
        }
    }

    @Test
    public void testVerifyExistentNameAttr() {
        try {
            String pathFile = "/docs/DataDictionary.txt";
            Dictionary dictionary = new Dictionary(pathFile);
            String nameTag = "div";
            List<String> listNameAttr = dictionary.getListNameAttr(nameTag);
            String nameAttr = "class";
            assertTrue(listNameAttr.contains(nameAttr));
        } catch (IOException ex) {
            assertFalse(false);
        }
    }

    @Test
    public void testVerifyNoExistentNameAttr() {
        try {
            String pathFile = "/docs/DataDictionary.txt";
            Dictionary dictionary = new Dictionary(pathFile);
            String nameTag = "div";
            List<String> listNameAttr = dictionary.getListNameAttr(nameTag);
            String nameAttr = "attr";
            assertFalse(listNameAttr.contains(nameAttr));
        } catch (IOException ex) {
            assertFalse(false);
        }
    }

}
