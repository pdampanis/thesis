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
import text.term.Word;

public class Document {

    private static final AtomicInteger count = new AtomicInteger(0);
    protected int id;
    protected String name;
    protected String content;
    public List<Word> terms;
    public List<String> sentences;
    public List<Word>[] termsBySentence;
    

    public Document(String name) {
        this.name = name;
        this.id = count.incrementAndGet();
        loadFile(name);
        sentences = getAllSentences();
        
        
        for(String sentence : sentences){
            List<String> preStems = getTermsBySentence(sentence);
            for(String s : preStems){
                //TODO: stem and insert it
            }
        }
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

    public List<Word> getTerms() {
        List<String> strs = createTextExtractor().extractTerms();
        List<Word> wordList = new ArrayList<Word>();
        for (String str : strs) {
            wordList.add(new Word(str));
        }
        TermCollection tc = new TermCollection(wordList);
        tc.insertAllTerms();
        List<Word> finalTerms = tc.getFinalTerms();
        return finalTerms;
    }
    
    public void setTerms(List<Word> wordList){
        for(Word word : wordList){
            terms.add(word);
        }
    }

    public List<String> getAllSentences() {
        sentences = createTextExtractor().extractSentences();
        TermCollection tc = new TermCollection();
        tc.setSentences(sentences);
        return sentences;
    }

    //TODO: this extracts only terms not stems with their frequency
    public List<String> getTermsBySentence(String sentence) {
        return new TextExtractor(sentence).extractTerms();
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
