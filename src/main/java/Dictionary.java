import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Locale;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dictionary {
    @XmlElement(name="dictionary")
    private ArrayList<Word> wordList;

    private static Dictionary instance;

    private Dictionary(){
        wordList = new ArrayList<Word>();

    }

    //singleton class
    public synchronized static Dictionary getInstance(){
        if(instance==null){
            instance = new Dictionary();
        }
        return instance;
    }

    public synchronized ArrayList<Word> getDictionary(){
        return new ArrayList<>(wordList);
    }

    public synchronized void setDictionary(ArrayList<Word> wordList){
        this.wordList = wordList;
    }

    public synchronized boolean addWord(Word word){
        if(wordList.contains(word)){
            return false;
        }
        else{
            wordList.add(word);
            return true;
        }
    }

    public Word getByWord(String word){
        ArrayList<Word> dictionaryCopy = getDictionary();

        for(Word w: dictionaryCopy){
            if(w.getWord().toLowerCase().equals(word.toLowerCase()))
                return w;
        }
        return null;
    }

    public Word modifyDescription(Word word){
        ArrayList<Word> dictionaryCopy = Dictionary.getInstance().getDictionary();

        for(Word w: dictionaryCopy){
            if(w.getWord().toLowerCase().equals(word.getWord().toLowerCase())){
                w.setDefinition(word.getDefinition());
            }

        }
        return null;
    }


}
