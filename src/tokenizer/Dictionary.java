package tokenizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.ReaderFile;

/**
 *
 * @author arquitectura de software II-2015
 */
public class Dictionary {

    private final Map<String, List<String>> dictionary;

    public Dictionary(String pathFile) throws IOException {
        dictionary = registerData(pathFile);
    }

    public Map<String, List<String>> registerData(String pathFile) throws FileNotFoundException, IOException {
        Map<String, List<String>> map = new HashMap<>();
        ReaderFile readerFile = new ReaderFile(pathFile, false);
        List<String> listText = readerFile.getListText();
        List<String> listData;
        String key;
        for (String text : listText) {
            listData = getListData(text);
            if (!listData.isEmpty()) {
                key = listData.remove(0);
                map.put(key, listData);
            }
        }
        return map;
    }

    private List<String> getListData(String string) {
        List<String> listData = new LinkedList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+[0-9]*");
        Matcher matcher = pattern.matcher(string);
        String word;
        while (matcher.find()) {
            word = string.substring(matcher.start(), matcher.end());
            listData.add(word);
        }
        return listData;
    }

    public boolean existNameTag(String nameTag) {
        boolean isValid = false;
        if (nameTag != null) {
            isValid = dictionary.containsKey(nameTag);
        }
        return isValid;
    }

    public List<String> getListNameAttr(String nameTag) {
        List<String> listNameAttr = null;
        if (nameTag != null && dictionary.containsKey(nameTag)) {
            listNameAttr = dictionary.get(nameTag);
        }
        return listNameAttr;
    }

}
