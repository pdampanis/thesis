package text.summarizer;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import text.document.Document;
import text.term.TermCollection;
import text.term.TermPreprocessor;
import text.term.Word;

public class Summarizer {

    private List<Document> Documents;
    private String[] keywords = null;
    private TermCollection termCollection;
    private Map<Integer,Double> TfIdfSentenceScoreMap;

    public String loadFile(String inputFile) {
        inputDoc = new Document(inputFile);
        return inputDoc.loadFile(inputFile);
    }
    public String loadText(String text){
        inputDoc = new Document(text);
        inputDoc.setContent(text);
        return inputDoc.getContent();
    }

    public void setKeywords() {

        List<String> processedTermList = new ArrayList<String>();
        TermPreprocessor tp = new TermPreprocessor();

        String resultTerm = null;
        List<String> terms = inputDoc.getAllTerms();

        for (String term : terms) {
            resultTerm = tp.preprocess(term);

            if (resultTerm != null)
                processedTermList.add(resultTerm);
        }

        this.keywords = processedTermList.toArray(new String[processedTermList.size()]);
    }
    
    public Map<Integer,Double> getTfIdfSentenceScoreMap(){
        return this.TfIdfSentenceScoreMap;
    }

    public void printKeywords() {
        System.out.println("#########################");
        for (String keyword : keywords) {
            System.out.println(keyword);
        }
    }

    public List<String> getAllSentences() {
        return inputDoc.getAllSentences();
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
    
    public List<String> getAllTerms() {
        return inputDoc.getAllTerms();
    }
    //adasd
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