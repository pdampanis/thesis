package text.summarizer;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.util.ArrayList;
import text.document.Document;
import text.document.Sentence;
import text.term.Word;

public class Summarizer {

    private ArrayList<Document> docs;
    private int numberOfDocs;

    public Summarizer(ArrayList<Document> _docs) {
        docs = new ArrayList<Document>();
        docs = _docs;
        numberOfDocs = docs.size();
    }

    // TODO: summarize - use the linear approach using all the statistical measurements
    public void summarize() {
        calculateTermWeights();
        calculateIDF();
        initiateSentences();
        System.out.println("++++++++++++++++++++++++++++++\n");
        //calculateTF_RIDF();
        //calculateTF_IDF();
        calculateTF_ISF();
    }

    // compute Term Frequency - TF
    public void calculateTermWeights() {
        int totalFrequency = 0;
        for (Document doc : docs) {
            totalFrequency = getTotalFrequency(doc.terms);
            for (Word word : doc.terms) {
                word.termWeight = word.getFrequency() / (double) totalFrequency;
            }
        }
    }

    public int getTotalFrequency(ArrayList<Word> terms) {
        int totalFrequency = 0;
        for (Word word : terms) {
            totalFrequency += word.getFrequency();
        }
        return totalFrequency;
    }

    public void calculateIDF() {
        for (Document doc : docs) {
            for (Word word : doc.terms) {
                word.termWeightInACollection = Math.log10(numberOfDocs / getDocFreq(word));
            }
        }
    }

    // Sentence Weighting based on TF*IDF
    public void calculateTF_IDF() {
        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                Double sum = 0.0;
                for (Word word : sentence.terms) {
                    sum += word.termWeight * word.termWeightInACollection;
                }
                sentence.TF_IDF_weight = sum;
                System.out.println(sentence.value + "==================================\n" + sentence.TF_IDF_weight);
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    // Sentence weighting based on TF*ISF
    public void calculateTF_ISF() {
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
    }

    // Sentence weighting based on TF*RIDF
    public void calculateTF_RIDF() {
        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                Double sum = 0.0;
                for (Word word : sentence.terms) {
                    Double rIDF = word.termWeightInACollection + Math.log10(1 - Math.exp(-(totFreq(word) / Double.valueOf(numberOfDocs * 1.0))));
                    sum += word.termWeight * rIDF;
                }
                sentence.TF_RIDF_weight = sum;
                System.out.println(sentence.value + "==================================\n" + sentence.TF_RIDF_weight);
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    public Double getDocFreq(Word word) {
        Double wordInDocs = 0.0;
        for (Document doc : docs) {
            if (contains(word, doc.terms)) {
                wordInDocs += 1.0;
            }
        }
        return wordInDocs;
    }

    public boolean contains(Word word, ArrayList<Word> terms) {
        for (Word term : terms) {
            if (word.value.equals(term.value)) {
                return true;
            }
        }
        return false;
    }

    public int ns(Word word, ArrayList<Sentence> sentences) {
        int ns = 0;
        for (Sentence sentence : sentences) {
            if (contains(word, sentence.terms)) {
                ns++;
            }
        }
        return ns;
    }

    public Double totFreq(Word word) {
        Double totalFrequencyAmongDocs = 0.0;
        for (Document doc : docs) {
            Word w = getWord(word, doc.terms);
            if (w != null) {
                totalFrequencyAmongDocs += w.frequency * 1.0;
            }
        }
        return totalFrequencyAmongDocs;
    }

    public Word getWord(Word word, ArrayList<Word> terms) {
        for (Word term : terms) {
            if (word.value.equals(term.value)) {
                return term;
            }
        }
        return null;
    }

    public void initiateSentences() {
        for (Document doc : docs) {
            for (Sentence sentence : doc.sentences) {
                sentence.setAllTerms(doc.terms);
            }
        }
    }
}