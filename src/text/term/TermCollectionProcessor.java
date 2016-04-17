package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
import java.util.List;

public class TermCollectionProcessor {

    private TermCollection termCollection;

    public TermCollectionProcessor() {
        termCollection = new TermCollection();
    }

    public TermCollection getTermCollection() {
        return termCollection;
    }

    public Word findWordByValue(String value) {
        List<Word> wordList = getTermCollection().getWordList();

        for (Word word : wordList) {
            String strValue = word.getValue();
            if (value.equals(strValue)) {
                return word;
            }
        }

        return null;
    }

    private void insertTerm(String value) {
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
        getTermCollection().getWordList().add(new Word(preprocessValue));

    }

    public String preprocess(String term) {
        TermPreprocessor tp = new TermPreprocessor();
        String result = null;
        result = tp.preprocess(term);

        return result;
    }

    public void insertAllTerms(String[] values) {
        for (String value : values) {
            insertTerm(value);
        }
    }
}