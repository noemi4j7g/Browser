package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface Attr extends Node{
    static final short typeNode = ATTRIBUTE_NODE;
    public String getName();
    public boolean getSpecified();
    public String getValue();
    public void setValue(String value);
    public Element getOwnerElement();
    public TypeInfo getSchemaTypeInfo();
    public boolean isId();
}
