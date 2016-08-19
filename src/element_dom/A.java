package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class A extends ElementPublic{

    public A() {
        super("a");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
