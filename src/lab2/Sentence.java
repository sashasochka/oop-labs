package lab2;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<SentencePart> parts;

    public Sentence() {
        this.parts = new ArrayList<>();
    }

    public Sentence(List<SentencePart> parts) {
        this.parts = parts;
    }

    public void addPart(SentencePart part) {
        parts.add(part);
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for (SentencePart sentencePart : parts) {
            result.append(sentencePart);
        }
        return result.toString();
    }

    final public List<Word> getWords() {
        // stub implementation
        return new ArrayList<>(); // TODO
    }

    public List<SentencePart> getParts() {
        return parts;
    }
}
