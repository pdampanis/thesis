package text.summarizer;

import java.util.List;
import text.term.TermCollectionProcessor;
import text.term.Word;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Test_TextSummarizer {

    public static void main(String[] args) {

        //todo: Maybe store or give the option to add files
        // but the summarizer will handle one file every time unless we change this.
        String filePath = "SampleText.txt";

        String[] keywords = null;
        Summarizer summarizer = new Summarizer();

        System.out.println(filePath);
        String text = summarizer.loadFile(filePath);

        //System.out.println(text);
        // Extract text and get all sentences
        String[] allSentences = summarizer.getAllSentences();
        /*
        for (String sentence : allSentences) {
            System.out.println(sentence);
            System.out.println("++++++++++++++++++++++++");
        }*/
        //summarizer.setKeywords();
        //summarizer.printKeywords();
        
        //todo: Decide how multiple docs will be handled.
        TermCollectionProcessor tcp = new TermCollectionProcessor();
        tcp.insertAllTerms(summarizer.getAllTerms()); 
        List<Word> terms = tcp.getTermCollection().getWordList();
        
                
        for(Word term : terms){
            System.out.println(term.value + ", " + term.frequency + ", " + term.termWeight);
        }
        
        /*
        tcp.getTermCollection().calculateFrequencies();
        System.out.println("======================================");
        
        for(Word term : terms){
            System.out.println(term.value + ", " + term.frequency);
        }
        */


        /*
         String bodyContent = new HtmlPreprocessor().getBodyFromHtmlPage("https://el.wikipedia.org/wiki/%CE%97%CE%BD%CF%89%CE%BC%CE%AD%CE%BD%CE%B5%CF%82_%CE%A0%CE%BF%CE%BB%CE%B9%CF%84%CE%B5%CE%AF%CE%B5%CF%82_%CE%91%CE%BC%CE%B5%CF%81%CE%B9%CE%BA%CE%AE%CF%82");
         String text2 = summarizer.loadText(bodyContent);
         System.out.println(text2);
        
         String[] allSentences2 = summarizer.getAllSentences();
         for (String sentence : allSentences2) {
         System.out.println(sentence);
         System.out.println("++++++++++++++++++++++++");
         }
         summarizer.setKeywords();
         summarizer.printKeywords();
         */

//    generator.generateSignificantSentences();
        //System.out.println(generator.generateSummary());
    }
}