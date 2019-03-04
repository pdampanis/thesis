package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
import java.util.ArrayList;
import nnkstemmer.nnkstem;
import nnkstemmer.word_node;

public class TermPreprocessor {

    public String preprocess(String term) {
        String resultTerm = null;
        if (term != null) {
            resultTerm = convertToLowerCase(term);
        }

        if (resultTerm != null) {
            resultTerm = removePunctuations(resultTerm);
        }


        if (resultTerm != null) {
            resultTerm = toStem(resultTerm);
        }

        return resultTerm;
    }

    public String convertToLowerCase(String term) {
        return term.toLowerCase();
    }

    public String removePunctuations(String term) {
        char[] chars = term.toCharArray();
        int length = chars.length;

        int count = 0;
        for (char ch : chars) {
            if (!Character.isLetterOrDigit(ch)) {
                count++;
            }
        }

        if (count == length) {
            return null;
        } else {
            return term;
        }
    }

    public String toStem(String term) {
        nnkstem stemmer = new nnkstem();
        ArrayList<word_node> word = new ArrayList<word_node>();
        stemmer.StrToWords(term.trim(), word);
        stemmer.rswas(word);
        String stem = null;
        if (isAStem(word.get(0).toString())) {
            stem = getStem(word.get(0).toString());
            if(isNumeric(stem))
                stem = null;
        }
        return stem;
    }

    public String getStem(String word) {
        String[] parts = word.split("\\(");
        String[] p = parts[1].split("\\)");
        return p[0];
    }

    private boolean isAStem(String word) {
        try {
            return word.endsWith("<Stem>");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}