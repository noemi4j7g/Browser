package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class Link extends ElementPublic{

    public Link() {
        super("link");
        
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
