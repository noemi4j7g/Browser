package dom_public;

import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public interface NodePublic {
    
    public void accept(Visitor visitor);
    
}
