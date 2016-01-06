package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class TermCollection implements Iterable<Word> {

    private List<Word> wordList;
    private Map<String, Integer> termFreqMap;

    public TermCollection() {
        wordList = getInitialWordList();
    }

    protected List<Word> getInitialWordList() {
        return new ArrayList<Word>();
    }

    @Override
    public Iterator<Word> iterator() {
        return wordList.iterator();
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
            if(freq == null)
                freq = 1;
            else {
                term.incrementFrequency();
            }
            termFreqMap.put(term.value, term.getFrequency());
        }
    }

    public int getTotalFrequency() {
        int totalFrequency = 0;
        for (Word word : this) {
            totalFrequency += word.getFrequency();
        }

        return totalFrequency;
    }

    public Word[] toArray() {
        return wordList.toArray(new Word[wordList.size()]);
    }

    public String[] toValuesArray() {
        List<String> values = new ArrayList<String>();
        for (Word word : this) {
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
}