package parser;

import dom_public.NodePublic;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arquitectura de software II-2015
 */
public class ParserElementText extends Parser{

    public ParserElementText(ReviewTokenizer reviewTok) {
        super(reviewTok);
    }

    @Override
    public List<NodePublic> parse() {
        List<NodePublic> listElementText = new ArrayList<>();
        ParserSimpleText parserSimpleText = new ParserSimpleText(reviewTok);
        listElementText.addAll(parserSimpleText.parse());
        ParserNestedText parserNestedText = new ParserNestedText(reviewTok);
        listElementText.addAll(parserNestedText.parse());
        return listElementText;
    }
      
}
