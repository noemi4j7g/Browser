package rendering;

import java.awt.Color;

/**
 *
 * @author arquitectura de software II-2015
 */
public class VerifierColor {

    public Color verifyColor(String valueProperty) {
        Color color = null;
        int sizeValue = valueProperty.length();
        if (valueProperty.startsWith("#") && sizeValue == 7) {
            color = verifyColorHex(valueProperty);
        } else if (valueProperty.startsWith("rgb(") && sizeValue >= 10) {
            color = verifyColorRgb(valueProperty);
        }
        return color;
    }
    
    private Color verifyColorHex(String valueProperty) {
        Color color = null;
        int r, g, b;
        try {
            r = Integer.parseInt(valueProperty.substring(1, 3), 16);
            g = Integer.parseInt(valueProperty.substring(3, 5), 16);
            b = Integer.parseInt(valueProperty.substring(5, 7), 16);
            if (validValue(r) && validValue(g) && validValue(b)) {
                color = new Color(r, g, b);
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException);
        }
        return color;
    }

    private Color verifyColorRgb(String valueProperty) {
        Color color = null;
        valueProperty = valueProperty.substring(4);
        valueProperty = valueProperty.replace(")", "");
        String[] valueRgb = valueProperty.split(",");
        int r, g, b;
        if (valueRgb.length == 3) {
            try {
                r = Integer.parseInt(valueRgb[0].trim());
                g = Integer.parseInt(valueRgb[1].trim());
                b = Integer.parseInt(valueRgb[2].trim());
                if (validValue(r) && validValue(g) && validValue(b)) {
                    color = new Color(r, g, b);
                }
            } catch (NumberFormatException numberFormatException) {
                System.err.println(numberFormatException);
            }
        }
        return color;
    }
    
    private boolean validValue(int valueColor) {
        return valueColor >= 0 && valueColor <= 255;
    }
}
