package text.document;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import text.term.TermCollection;
import text.term.TermPreprocessor;
import text.term.Word;

public class  Document {

    private static final AtomicInteger count = new AtomicInteger(0);
    public int id;
    public String name;
    protected String content;
    public Sentence title;
    public ArrayList<Word> terms;
    public ArrayList<Sentence> sentences;
    public ArrayList<ArrayList<Word>> termsBySentence;
    public ArrayList<Paragraph> paragraphs;

    public Document(String name) {
        this.name = name;
        this.id = count.incrementAndGet();
        loadFile(name);
        terms = getTerms();
        paragraphs = new ArrayList<Paragraph>();
        sentences = new ArrayList<Sentence>();


        for (String paragraph : getAllParagraphs(name)){
            ArrayList<Sentence> sentenceList = getSentencesByParagraph(paragraph);
            termsBySentence = new ArrayList<ArrayList<Word>>(sentenceList.size());

            for (Sentence s : sentenceList){
                sentences.add(new Sentence(s.value, getTermsBySentence(s.value)));
                termsBySentence.add(getTermsBySentence(s.value));
            }
            paragraphs.add(new Paragraph(paragraph, sentences));
        }
        // Set the title of the document to be the first sentence of it
        title = sentences.get(0);

    }

    public void loadFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                if (scanner.hasNextLine()) {
                    sb.append("\n");
                }
            }
            
            scanner.close();
            setContent(sb.toString());
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Word> getTerms() {
        List<String> strs = createTextExtractor().extractTerms();
        List<Word> wordList = new ArrayList<Word>();
        for (String str : strs) {
            wordList.add(new Word(str));
        }
        TermCollection tc = new TermCollection(wordList);
        tc.insertAllTerms();
        ArrayList<Word> finalTerms = tc.getFinalTerms();
        return finalTerms;
    }

    public void setTerms(List<Word> wordList) {
        for (Word word : wordList) {
            terms.add(word);
        }
    }

    private ArrayList<Sentence> getSentencesByParagraph(String paragraph){
        ArrayList<String> sentences = new TextExtractor(paragraph).extractSentences();
        ArrayList<Sentence> sentencesByParagraph = new ArrayList<Sentence>();
        for (String sentence : sentences) {
            ArrayList<Word> termList = getTermsBySentence(sentence);
            sentencesByParagraph.add(new Sentence(sentence, termList));
        }
        return sentencesByParagraph;
    }
    
    public ArrayList<String> getAllParagraphs(String name) {
        return createTextExtractor().extractParagraphs(name);
    }

    public ArrayList<Word> getTermsBySentence(String sentence) {
        ArrayList<String> strs = new TextExtractor(sentence).extractTerms();
        ArrayList<Word> wordList = new ArrayList<Word>();
        for (String str : strs) {
            String temp = new TermPreprocessor().preprocess(str);
            if (temp != null) {
                wordList.add(new Word(temp));
            }
        }
        return wordList;
    }

    private TextExtractor createTextExtractor() {
        TextExtractor text = new TextExtractor();
        text.setText(getContent());
        return text;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }
}
