package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class H1 extends ElementPublic{

    public H1() {
        super("h1");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
