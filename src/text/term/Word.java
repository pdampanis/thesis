package text.term;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class Word {

    public String value;
    public int frequency = 1;
    public Double relativeFrequency;
    public Double termWeight;
    public Double termWeightInACollection;

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
}