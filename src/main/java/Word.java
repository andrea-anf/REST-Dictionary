import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Word {
    private String word;
    private String definition;

    public Word(){}
    public Word(String word, String definition){
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }
}
