package text.document;

import java.util.ArrayList;
import text.term.Word;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Sentence {

    public String value;
    public ArrayList<Word> terms;
    public ArrayList<Word> allTerms;
    public Double TF_IDF_weight;
    public Double TF_ISF_weight;
    public Double TF_RIDF_weight;
    public Double baxendaleValue;
    public Double newsArticleValue;
    public Double TT;

    public Sentence(String _value, ArrayList<Word> _terms) {
        value = _value;
        terms = new ArrayList<Word>();
        terms = _terms;
    }
    
    public void setAllTerms(ArrayList<Word> _allterms){
        allTerms = _allterms;
        findAndCopyWordProperties();
    }
    
    private void findAndCopyWordProperties(){
        for(Word term : allTerms){
            for(Word word : terms){
                if(word.value.equals(term.value)){
                    word.frequency = term.frequency;
                    word.termWeight = term.termWeight;
                    word.termWeightInACollection = term.termWeightInACollection;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Word word : terms){
            sb.append(word.value + " " + word.termWeight + " " + word.termWeightInACollection);
        }
        return sb.toString();
    }
}
