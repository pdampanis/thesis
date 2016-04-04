package text.summarizer;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import text.term.Word;

public class Summarizer {

    private int numberOfDocs;
    private ArrayList<Word> finalTerms;
    private Map<Integer, Double> TfIdfSentenceScoreMap;
    
    public Summarizer(ArrayList<Word> _finalTerms, int _numberOfDocs){
        numberOfDocs = _numberOfDocs;
        finalTerms = _finalTerms;
    }

    // compute Term Frequency - TF
    public ArrayList<Word> getTermWeights() {
        int totalFrequency = getTotalFrequency();
        for (Word word : finalTerms) {
            word.termWeight = word.getFrequency() / (double) totalFrequency;
        }
        return finalTerms;
    }
    
    // compute IDF

    public int getTotalFrequency() {
        int totalFrequency = 0;
        for (Word word : finalTerms) {
            totalFrequency += word.getFrequency();
        }
        return totalFrequency;
    }
    /*
     public Map<Integer,Double> getTfIdfSentenceScoreMap(){
     return this.TfIdfSentenceScoreMap;
     }


    
     public void sentenceWeighting(TermCollectionProcessor tcp){
        
     TfIdfSentenceScoreMap = new HashMap<Integer,Double>();
     List<String> sentences = getAllSentences();
     List<String> terms = new ArrayList<String>();
     int sentenceCount = 0;
     for(String sentence : sentences){
     terms = inputDoc.getTermsBySentence(sentence);
     List<Word> wordList = tcp.computeTermWeighting(terms);
     Double sum = 0.0;
     for(Word word : wordList){
     sum += word.termWeight * 1;// todo: compute IDF
     }
     TfIdfSentenceScoreMap.put(sentenceCount++, sum);
     sum = 0.0;
     }
        
     }
    

     /*
     public String generateSummary() {
     String[] significantSentences = generateSignificantSentences();
     // ***TO WRITE REST OF CODE
     }

     public String[] generateSignificantSentences() {
     String[] allSentences = getAllSentences();
     double[] scores = calcAllSentenceScores();
     // ***TO WRITE REST OF CODE
     }

     public String[] getAllSentences() {
     return inputDoc.getAllSentences();
     }

     public double[] calcAllSentenceScores() {
     // ***TO WRITE REST OF CODE
     }
     * */
}