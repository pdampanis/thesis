package text.document;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class TextExtractor {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextExtractor() {
    }

    public TextExtractor(String _text) {
        this.text = _text;
    }

    public ArrayList<String> extractTerms() {
        BreakIterator boundary = BreakIterator.getWordInstance(Locale.US);
        boundary.setText(this.text);

        ArrayList<String> words = new ArrayList<String>();
        int start = boundary.first();
        int end = boundary.next();
        while (end != BreakIterator.DONE) {
            String word = this.text.substring(start, end).trim();
            if (!word.isEmpty()) {
                words.add(word);
            }
            start = end;
            end = boundary.next();
        }

        return words;
    }

    public ArrayList<String> extractSentences() {
        BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.US);
        boundary.setText(getText());

        ArrayList<String> sentences = new ArrayList<String>();
        int start = boundary.first();
        int end = boundary.next();
        while (end != BreakIterator.DONE) {
            String sentence = getText().substring(start, end).trim();
            if (!sentence.isEmpty()) {
                //System.out.println("ΩΩΩΩΩΩΩΩΩΩ " + sentence + " " + sentence.charAt(sentence.length() - 1));
                sentences.add(sentence);
            }
            start = end;
            end = boundary.next();
        }

        return sentences;
    }

    public ArrayList<String> extractParagraphs() {
        BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.US);
        boundary.setText(getText());

        ArrayList<String> sentences = new ArrayList<String>();
        int start = boundary.first();
        int end = boundary.next();
        while (end != BreakIterator.DONE) {
            String sentence = getText().substring(start, end).trim();
            if (!sentence.isEmpty()) {
                //System.out.println("ΩΩΩΩΩΩΩΩΩΩ " + sentence);
                sentences.add(sentence);
            }
            start = end;
            end = boundary.next();
        }

        return sentences;
    }
}