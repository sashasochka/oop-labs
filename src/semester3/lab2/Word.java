package semester3.lab2;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;
import java.util.List;

public class Word implements SentencePart {
    private List<Symbol> letters;

    /** Construct empty word */
    public Word() {
        this.letters = new ArrayList<>();
    }

    /** Constuct a word from list of letters */
    public Word(List<Symbol> letters) {
        this.letters = letters;
    }

    /** Add a letter to a word */
    public void addLetter(Symbol letter) {
        letters.add(letter);
    }

    /** Get a list of letters */
    final public List<Symbol> getLetters() {
        return letters;
    }

    /** Count letter occurences */
    public int countLetter(Symbol letter) {
        return CharMatcher.is(letter.toLower().getChar()).countIn(toString().toLowerCase());
    }

    @Override
    public boolean isWord() {
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Symbol letter : letters) {
            result.append(letter);
        }
        return result.toString();
    }

    /** Check if this word is empty */
    public boolean isEmpty() {
        return letters.isEmpty();
    }
}
