package rendering;

import java.awt.Color;
import java.awt.Font;
import java.util.Objects;

/**
 *
 * @author arquitectura de software II-2015
 */
public class LayoutElement {

    private Color background_color;
    private Color color_text;
    private Font font;
    private boolean hasAssignedProperties;
    private final VerifierColor verifierColor;
    private final VerifierFont verifierFont;

    public LayoutElement() {
        verifierColor = new VerifierColor();
        verifierFont = new VerifierFont();
        background_color = null;      
        font = null;
        hasAssignedProperties = false;
    }
    
    public void assignDefaultProperties() {
        //background_color = new Color(255, 255, 255);
       // color_text = new Color(0, 0, 0);
        font = new Font("TimesRoman", Font.PLAIN, 12);
        hasAssignedProperties = true;
    }

    public void assignProperties(Color background_color, Color color_text, Font font) {
        this.background_color = background_color;
        this.color_text = color_text;
        this.font = font;
        hasAssignedProperties = true;
    }

    public Color getBackgroundColor() {
        return background_color;
    }

    public Color getColorText() {
        return color_text;
    }

    public Font getFont() {
        return font;
    }

    public void setProperty(String nameProperty, String valueProperty) {
        if (nameProperty != null && valueProperty != null) {
            valueProperty = valueProperty.toLowerCase().trim();
            nameProperty = nameProperty.toLowerCase().trim();
            switch (nameProperty) {
                case "color":
                    Color color = verifierColor.verifyColor(valueProperty);
                    if (color != null) {
                        color_text = color;
                        hasAssignedProperties = true;
                    }
                    break;
                case "background-color":
                    Color back_color = verifierColor.verifyColor(valueProperty);
                    if (back_color != null) {
                        background_color = back_color;
                        hasAssignedProperties = true;
                    }
                    break;
                case "text-decoration":
                    Font font_text_decoration = verifierFont.verifyTextAttribute(valueProperty, font);
                    if (font_text_decoration != null) {
                        font = font_text_decoration;
                        hasAssignedProperties = true;
                    }
                    break;
                case "font-family":
                    Font font_family = verifierFont.verifyFont(valueProperty);
                    if (font_family != null) {
                        font = font_family;
                        hasAssignedProperties = true;
                    }
                    break;
                case "font-size":
                    int sizeFont = verifierFont.verifySize(valueProperty);
                    if (sizeFont > 0) {
                        if (font != null) {
                            font = font.deriveFont(sizeFont);
                        } else {
                            font = new Font("serif", Font.PLAIN, sizeFont);
                        }
                        hasAssignedProperties = true;
                    }
                    break;
                default:
                    System.err.println("The attribute is not. The names of valid properties are in /src/docs/Readme.txt");
                    break;
            }
        }
    }

    public boolean hasAssignedProperty() {
        return hasAssignedProperties;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.background_color);
        hash = 47 * hash + Objects.hashCode(this.color_text);
        hash = 47 * hash + Objects.hashCode(this.font);
        hash = 47 * hash + (this.hasAssignedProperties ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && getClass() == obj.getClass()) {
            final LayoutElement other = (LayoutElement) obj;
            isEqual = this.background_color != null?this.background_color.equals(other.background_color):other.background_color == null;
            isEqual = this.color_text != null?isEqual && this.color_text.equals(other.color_text):isEqual && other.color_text == null;
            isEqual = this.font != null?isEqual && this.font.equals(other.font):isEqual && other.font == null;
            isEqual = isEqual && this.hasAssignedProperties == other.hasAssignedProperties;
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "LayoutElement{" + "background_color=" + background_color + ", color_text=" + color_text + ", font=" + font + ", hasAssignedProperties=" + hasAssignedProperties + '}';
    }

}
