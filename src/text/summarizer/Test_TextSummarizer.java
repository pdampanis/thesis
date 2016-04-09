package text.summarizer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import text.document.Document;
import text.document.Sentence;
import text.term.TermCollection;
import text.term.Word;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Test_TextSummarizer {

    public static void main(String[] args) {

        File f = new File("greek_texts");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        ArrayList<Document> docs = new ArrayList<Document>();

        File[] files = f.listFiles(textFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.print("directory:");
            } else {
                // is a file
                Document doc = new Document(file.getAbsolutePath());
                docs.add(doc);
                /*
                 System.out.println(doc.id + " " + doc.name);
                
                 for(Word word : doc.terms){
                 System.out.println(word.value + " " + word.frequency);
                 }
                 //TODO: Compute Weight functions 
                 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                 for(ArrayList<Word> a : doc.termsBySentence){

                 for(Word w : a){
                 System.out.println(w.value + " " + w.frequency);
                 }
                 System.out.println("===============================================================");
                 }
                 * */

            }
        }
        int numberOfDocuments = docs.size();
        // TF, IDF
        for (Document doc : docs) {
            System.out.println("==========================================");
            System.out.println(doc);
            Summarizer summarizer = new Summarizer(doc.terms, numberOfDocuments);
            summarizer.getTermWeights();
            for (Word word : doc.terms) {
                word.termWeightInACollection = Math.log10(numberOfDocuments / getDocFreq(word, docs));
            }
        }

        /*
        // Sentence Weighting - TF*IDF
        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                sentence.setAllTerms(doc.terms);
                Double sum = 0.0;
                for (Word word : sentence.terms) {
                    sum += word.termWeight * word.termWeightInACollection;
                }
                sentence.TF_IDF_weight = sum;
                System.out.println(sentence.value + "==================================\n" + sentence.TF_IDF_weight);
            }
        }
        * */
        // Compute Sentence weighting based on TF*ISF
        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                sentence.setAllTerms(doc.terms);
            }
        }

        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                Double sum = 0.0;
                for (Word word : sentence.terms) {
                    sum += word.termWeight * ns(word, doc.sentences);
                }
                sentence.TF_ISF_weight = sum;
                System.out.println(sentence.value + "==================================\n" + sentence.TF_ISF_weight);
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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

    public static Double getDocFreq(Word word, ArrayList<Document> docs) {
        Double wordInDocs = 0.0;
        for (Document doc : docs) {
            if (contains(word, doc.terms)) {
                wordInDocs += 1.0;
            }
        }
        return wordInDocs;
    }

    public static boolean contains(Word word, ArrayList<Word> terms) {
        for (Word term : terms) {
            if (word.value.equals(term.value)) {
                return true;
            }
        }
        return false;
    }

    public static int ns(Word word, ArrayList<Sentence> sentences) {
        int ns = 0;
        for (Sentence sentence : sentences) {
            if (contains(word, sentence.terms)) {
                ns++;
            }
        }
        return ns;
    }
}