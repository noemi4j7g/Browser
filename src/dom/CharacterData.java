package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface CharacterData extends Node {
    
    static final short nodeType = CDATA_SECTION_NODE;
    
    public String getData();
    public void setData(String data);
    public int getLength();
    public String substringData(int offset,int count);
    public void appendData(String dataToAdd);
    public void insertData(int offset, String arg);
    public void deleteData(int offset,int count);
    public void replaceData(int offset,int count,String arg);
}
