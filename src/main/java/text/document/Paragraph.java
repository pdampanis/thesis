package text.document;

import java.util.ArrayList;
/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Paragraph {

    public String value;
    public ArrayList<Sentence> sentences;

    public Paragraph(String _value, ArrayList<Sentence> _sentences){
        value = _value;
        sentences = _sentences;
    }

}
