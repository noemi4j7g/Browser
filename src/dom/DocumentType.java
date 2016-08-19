package dom;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface DocumentType {

    public String getName();
    public NamedNodeMap getEntities();
    public NamedNodeMap getNotations();
    public String getPublicId();
    public String getSystemId();
    public String getInternalSubset();
}

