package lab2;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<SentencePart> parts;

    /** Default constructor for empty sentences */
    public Sentence() {
        this.parts = new ArrayList<>();
    }

    /** Construct a sentence from already created parts */
    public Sentence(List<SentencePart> parts) {
        this.parts = parts;
    }

    /** Add a part of the sentence */
    public void addPart(SentencePart part) {
        parts.add(part);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean firstWord = true;
        for (SentencePart part : parts) {
            if (!firstWord && part.isWord()) {
                result.append(" ");
            }
            firstWord = false;
            result.append(part);
        }
        return result.toString();
    }

    /** Get list of words in this sentence */
    final public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (SentencePart part : parts) {
            if (part instanceof Word) {
                words.add((Word) part);
            }
        }
        return words;
    }

    /** Get all parts of the sentence */
    public List<SentencePart> getParts() {
        return parts;
    }

    /** Check if this sentence is empty */
    public boolean isEmpty() {
        return getWords().isEmpty();
    }
}
