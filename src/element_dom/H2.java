package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class H2 extends ElementPublic{

    public H2() {
        super("h2");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
