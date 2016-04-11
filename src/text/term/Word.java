package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Word implements Comparable<Word> {

    public String value;
    public int frequency = 1;
    public Double relativeFrequency;
    public Double termWeight;
    public Double termWeightInACollection;
    public Word() {
        super();
    }

    public Word(String value) {
        this.termWeight = 0.0;
        this.termWeightInACollection = 0.0;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void settermWeight(Double weight){
        this.termWeight = weight;
    }
    
    public Double gettermWeight(){
        return termWeight;
    }

    public int getFrequency() {
        return frequency;
    }

    public int incrementFrequency() {
        return ++frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int length() {
        return getValue().length();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    public int compareTo(Word otherWord) {
        int count1 = getFrequency();
        int count2 = otherWord.getFrequency();
        if (count1 < count2) {
            return 1;

        } else if (count1 > count2) {
            return -1;

        } else {
            // ... If counts are equal, compare keys alphabetically.
            return getValue().compareTo(otherWord.getValue());
        }
    }
}