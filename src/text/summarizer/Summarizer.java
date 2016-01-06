package text.summarizer;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.util.List;
import java.util.ArrayList;
import text.document.Document;
import text.term.TermCollection;
import text.term.TermPreprocessor;

public class Summarizer {

    private Document inputDoc;
    private String[] keywords = null;
    private TermCollection termCollection;

    public String loadFile(String inputFile) {
        inputDoc = new Document();
        return inputDoc.loadFile(inputFile);
    }
    public String loadText(String text){
        inputDoc = new Document();
        inputDoc.setContent(text);
        return inputDoc.getContent();
    }

    public void setKeywords() {

        List<String> processedTermList = new ArrayList<String>();
        TermPreprocessor tp = new TermPreprocessor();

        String resultTerm = null;
        String[] terms = inputDoc.getAllTerms();

        for (String term : terms) {
            resultTerm = tp.preprocess(term);

            if (resultTerm != null)
                processedTermList.add(resultTerm);
        }

        this.keywords = processedTermList.toArray(new String[processedTermList.size()]);
    }


    public void printKeywords() {
        System.out.println("#########################");
        for (String keyword : keywords) {
            System.out.println(keyword);
        }
    }

    public String[] getAllSentences() {
        return inputDoc.getAllSentences();
    }
    
    public String[] getAllTerms() {
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