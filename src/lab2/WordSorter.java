package lab2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordSorter {
    public static void main(String[] args) {
        /** the letter for sorting words by number of its occurences */
        final Symbol indexingLetter = new Symbol(Character.toLowerCase('o'));
        /** string which contains words to be sorted */
        final String textString = "Hello, how are you? Ooo oooo Oo    oo you!";

        /** Text object containing OO representation of text string */
        Text text = new Text(textString);
        List<Word> words = text.getWords();
        /** Sort words by number of indexingLetter occurences */
        /** use custom Comparator */
        Collections.sort(words, new Comparator<Word>() {
            /**
             *
             * @param w1 First word to compare
             * @param w2 Second word to compare
             * @return The same semantics as in overriden method
             */
            @Override
            public int compare(final Word w1, final Word w2) {
                return w1.countLetter(indexingLetter) - w2.countLetter(indexingLetter);
            }
        });
        /** each word in list of words */
        for (Word word : words) {
            System.out.println(word);
        }
    }
}


