package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface ProcessingInstruction extends Node{
    public String getTarget();
    public String getData();
    public void setData(String data) throws DOMException;
}
