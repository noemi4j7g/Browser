package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arquitectura de software II-2015
 */
public class VerifierFont {

    public Font verifyFont(String valueProperty) {
        valueProperty = valueProperty.replace("\"", "");
        Font font = new Font(valueProperty, Font.PLAIN, 12);
        return font;
    }

    public int verifySize(String valueProperty) {
        int size = 0;
        valueProperty = valueProperty.replaceAll("px", "");
        valueProperty = valueProperty.replaceAll("%", "");
        valueProperty = valueProperty.replaceAll("em", "");
        try {
            size = Integer.parseInt(valueProperty);
            if (size < 2 || size > 90) {
                System.err.println("The font-size is invalid, must be: size > 2 and size < 90");
                size = 0;
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println("The property \"font-size\" is not valid. " + numberFormatException);
        }
        return size;
    }

    public Font verifyTextAttribute(String valueProperty, Font oldFont) {
        Font font = null;
        switch (valueProperty) {
            case "underline":
                Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                if (oldFont != null) {
                    font = oldFont.deriveFont(fontAttributes);
                } else {
                    font = new Font("TimeRoman", Font.PLAIN, 12).deriveFont(fontAttributes);
                }
                break;
            case "none":
                if(oldFont != null){
                    font = oldFont.deriveFont(Font.PLAIN);
                }else{
                    font = new Font("TimeRoman", Font.PLAIN, 12);
                }
            default:
                break;
        }
        return font;
    }

}
