package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word implements SentencePart {
    private List<Symbol> letters;

    public Word() {
        this.letters = new ArrayList<>();
    }

    public Word(List<Symbol> letters) {
        this.letters = letters;
    }

    public void addLetter(Symbol letter) {
        letters.add(letter);
    }

    final public List<Symbol> getLetters() {
        return letters;
    }

    public int countLetter(Symbol letter) {
        return Collections.frequency(letters, letter);
    }

    public boolean isWord() {
        return true;
    }
}
