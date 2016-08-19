package element_dom;

import dom_public.ElementPublic;
import visitor.Visitor;

/**
 *
 * @author arquitectura de software II-2015
 */
public class Br extends ElementPublic{

    public Br() {
        super("br");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
