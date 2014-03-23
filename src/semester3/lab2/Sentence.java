package semester3.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /** Add a part of this sentence */
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
        return parts.stream()
                .filter(part -> part instanceof Word).map(part -> (Word) part)
                .collect(Collectors.toList());
    }

    /** Get all parts of this sentence */
    public List<SentencePart> getParts() {
        return parts;
    }

    /** Check if this sentence is empty */
    public boolean isEmpty() {
        return getWords().isEmpty();
    }
}
