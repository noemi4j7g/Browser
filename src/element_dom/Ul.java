package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-205
 */
public class Ul extends ElementPublic{

    public Ul() {
        super("ul");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
