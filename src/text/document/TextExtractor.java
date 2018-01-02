package text.document;

/**
 *
 * @author Panagiotis Dampanis AM:070095
 */
import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
                sentences.add(sentence);
            }
            start = end;
            end = boundary.next();
        }

        for (String sentence : sentences) {
            if (sentence.contains("\n")) {
                ArrayList<String> newSentences = new ArrayList<String>();
                String[] allSentences = sentence.split("\n");
                for (String s : allSentences) {
                    newSentences.add(s);
                }
                return newSentences;
            }
        }

        return sentences;
    }

    public ArrayList<String> extractParagraphs(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            StringBuilder sb = new StringBuilder();
            ArrayList<String> paragraphs = new ArrayList<String>();

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.trim().endsWith(".") || str.trim().endsWith(";") || str.trim().endsWith("?") || str.trim().endsWith("!")) {
                    System.out.println(str.length());
                    if (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(str.length());
                        try {
                            if (Character.isUpperCase(line.trim().charAt(0))) {
                                sb.append(str);
                                paragraphs.add(sb.toString());
                                sb.delete(0, sb.length());
                                sb.append(line).append("\n");
                            } else {
                                sb.append(line).append("\n");
                            }
                        } catch (Exception e) {
                            ;
                        }
                    } else {
                        sb.append(str).append("\n");
                        paragraphs.add(sb.toString());
                    }
                } else {
                    sb.append(str).append("\n");
                }
            }
            return paragraphs;
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
        return null;
    }
}