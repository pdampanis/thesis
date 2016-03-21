package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class TermCollection {

    private List<Word> wordList;
    private List<Word> finalTerms;
    private List<String> sentences;
    private Map<String, Integer> termFreqMap;
    private Map<String, Double> TfIdfSentenceScoreMap;

    public TermCollection(List<Word> _wordList) {
        this.wordList = _wordList;
    }

    public TermCollection() {
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public List<Word> getFinalTerms() {
        return finalTerms;
    }

    public boolean isEmpty() {
        return wordList.isEmpty();
    }

    public ListIterator<Word> listIterator() {
        return wordList.listIterator();
    }

    public int numberOfWords() {
        return wordList.size();
    }

    public void calculateFrequencies() {
        termFreqMap = new HashMap<String, Integer>();
        for (Word term : wordList) {
            Integer freq = termFreqMap.get(term.value);
            if (freq == null) {
                freq = 1;
            } else {
                term.incrementFrequency();
            }
            termFreqMap.put(term.value, term.getFrequency());
        }
    }

    public int getTotalFrequency() {
        int totalFrequency = 0;
        for (Word word : finalTerms) {
            totalFrequency += word.getFrequency();
        }

        return totalFrequency;
    }

    public Word[] toArray() {
        return wordList.toArray(new Word[wordList.size()]);
    }

    public String[] toValuesArray() {
        List<String> values = new ArrayList<String>();
        for (Word word : wordList) {
            values.add(word.getValue());
        }

        return wordList.toArray(new String[values.size()]);
    }

    public String[] getWordValues() {
        List<String> values = new ArrayList<String>();
        for (Word word : wordList) {
            values.add(word.getValue());
        }

        return values.toArray(new String[values.size()]);
    }

    public int[] getFrequencyValues() {
        int[] values = new int[wordList.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = wordList.get(i).getFrequency();
        }

        return values;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public int size() {
        return wordList.size();
    }

    private Word findWordByValue(String value) {

        for (Word word : finalTerms) {
            String strValue = word.getValue();
            if (value.equals(strValue)) {
                return word;
            }
        }
        return null;
    }

    private void insertTerm(Word word) {
        String value = word.getValue();
        if (value == null) {
            return;
        }

        String preprocessValue = preprocess(value);

        if (preprocessValue == null) {
            return;
        }

        Word existingWord = findWordByValue(preprocessValue);
        if (existingWord != null) {
            existingWord.incrementFrequency();
            return;
        }
        // if term inserted for the first time
        finalTerms.add(new Word(preprocessValue));

    }

    private String preprocess(String term) {
        TermPreprocessor tp = new TermPreprocessor();
        String result = null;
        result = tp.preprocess(term);

        return result;
    }

    public void insertAllTerms() {
        for (Word term : wordList) {
            insertTerm(term);
        }
    }

    // Compute TF
    public List<Word> computeTermWeights() {
        int totalFrequency = getTotalFrequency();
        for (Word word : finalTerms) {
            //compute Term Frequency
            word.termWeight = word.getFrequency() / (double) totalFrequency;
        }
        return finalTerms;
    }

    //TODO:
    // Compute TF*IDF
    public void sentenceWeighting(int docId) {

        TfIdfSentenceScoreMap = new HashMap<String, Double>();
        int sentenceCount = 0;
        for (String sentence : sentences) {
            terms = inputDoc.getTermsBySentence(sentence);
            List<Word> wordList = tcp.computeTermWeighting(terms);
            Double sum = 0.0;
            for (Word word : wordList) {
                sum += word.termWeight * 1;// TODO: compute IDF
            }
            TfIdfSentenceScoreMap.put(sentenceCount++, sum);
        }
    }

    public void sort() {
        Word[] wordsArray = wordList.toArray(new Word[wordList.size()]);
        Arrays.sort(wordsArray);

        ListIterator<Word> i = wordList.listIterator();
        for (int j = 0; j < wordsArray.length; j++) {
            i.next();
            i.set(wordsArray[j]);
        }
    }
}