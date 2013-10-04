package lab2;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private List<Sentence> sentences;

    /** Default constructor - no sentences inside the Text */
    public Text() {
        this.sentences = new ArrayList<>();
    }

    /** Parse \a fromString to text using sentences, sentenceSeparators, words and symbols */
    public Text(String fromString) {
        this();
        Sentence curSentence = new Sentence();
        Word curWord = new Word();
        /**
         * Iterate over each word and construct
         * words, sentences, separator symbols and
         * this text object as result
         */
        for (char c : fromString.toCharArray()) {
            if (!SeparatorSymbol.charIsSeparator(c)) {
                // just a symbol
                Symbol letter = new Symbol(c);
                if (!letter.isWhitespace()) {
                    curWord.addLetter(letter);
                } else if (!curWord.isEmpty()) {
                    curSentence.addPart(curWord);
                    curWord = new Word();
                }
            } else {
                SeparatorSymbol separatorSymbol = new SeparatorSymbol(c);
                if (!curWord.isEmpty()) {
                    curSentence.addPart(curWord);
                    curWord = new Word();
                }
                curSentence.addPart(separatorSymbol);
                if (separatorSymbol.isSentenceSeparator() && !curSentence.isEmpty()) {
                    // sentence ended
                    addSentence(curSentence);
                    curSentence = new Sentence();
                }
            }
        }
        if (!curWord.isEmpty()) {
            curSentence.addPart(curWord);
        }
        if (!curSentence.isEmpty()) {
            addSentence(curSentence);
        }
    }

    /** Construct \a Text from list of sentenes */
    public Text(final List<Sentence> sentences) {
        this.sentences = sentences;
    }

    /** Add a sentences to this text */
    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean firstSentence = true;
        for (Sentence sentence : sentences) {
            if (!firstSentence) {
                result.append(" ");
            }
            firstSentence = false;
            result.append(sentence);
        }
        return result.toString();
    }

    /** Get list of words in this text */
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Sentence sentence : sentences) {
            words.addAll(sentence.getWords());
        }
        return words;
    }
}
