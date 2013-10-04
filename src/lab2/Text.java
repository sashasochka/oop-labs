package lab2;

import java.util.ArrayList;
import java.util.List;

public class Text implements SentencePart {
    private List<Sentence> sentences;

    public Text() {
        this.sentences = new ArrayList<>();
    }

    public Text(final List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for (Sentence sentence : sentences) {
            result.append(sentence);
        }
        return result.toString();
    }

    public boolean isWord() {
        return false;
    }
}
