package text.summarizer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import text.document.Document;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Test_TextSummarizer {

    public static void main(String[] args) {

        //@todo: Maybe store or give the option to add files
        // but the summarizer will handle one file every time unless we change this.

        File f = new File("C:\\Users\\Panos\\Documents\\NetBeansProjects\\TextSummarizer_1\\greek_texts");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };
        
        List<Document> docs = new ArrayList<Document>();
        File[] files = f.listFiles(textFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.print("directory:");
            } else {
                // is a file
                docs.add(new Document(file.getAbsolutePath()));
                
            }
        }

//        String filePath = "greek_texts\\SampleText_0.txt";
//        String[] keywords = null;
//        Summarizer summarizer = new Summarizer();
//
//        System.out.println(filePath);
//
//        summarizer.loadFile(filePath);
//
//        TermCollectionProcessor tcp = new TermCollectionProcessor();
//
//        tcp.insertAllTerms(summarizer.getAllTerms());
//        List<Word> terms = tcp.getTermCollection().getWordList();
//
//        summarizer.sentenceWeighting(tcp);
//        Map<Integer, Double> sentenceScoreMap = summarizer.getTfIdfSentenceScoreMap();
//
//        System.out.println(
//                "Sentences:");
//        Set<Integer> keys = sentenceScoreMap.keySet();
//        Iterator<Integer> iterator = keys.iterator();
//
//        while (iterator.hasNext()) {
//            int index = iterator.next();
//            Double score = sentenceScoreMap.get(index);
//            System.out.println(index + "  " + score);
//        }
        
        
        
        
        
        
        
        
        
//        for(Word term : terms){
//            System.out.println(term.value + ", " + term.frequency + ", " + term.termWeight);
//        }
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