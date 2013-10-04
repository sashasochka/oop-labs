package lab2;

import com.google.common.base.CharMatcher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordSorter {
    public static void main(String[] args) {
        /** the letter for sorting words by number of its occurences */
        final char indexingLetter = Character.toLowerCase('o');
        /** string which contains words to be sorted */
        final String textString = "Hello, how are you? Ooo oooo Oo    oo you!";

        /** check that indexingLetter is a letter */
        if (!Character.isLetter(indexingLetter)) {
            System.err.println(indexingLetter + " is not a letter!");
            System.exit(1);
        }

        /** Text object containing OO representation of text string */
        Text text = new Text(textString);
        List<Word> words = text.getWords();
        /** Sort words by number of indexingLetter occurences */
        /** use custom Comparator */
        Collections.sort(words, new Comparator<Word>() {
            /**
             *
             * @param \a w1 First word to compare
             * @param \a w2 Second word to compare
             * @return The same semantics as in overriden method
             */
            @Override
            public int compare(final Word w1, final Word w2) {
                // FIXME rewrite using countLetters method
                return CharMatcher.is(indexingLetter).countIn(w1.toString().toLowerCase()) -
                        CharMatcher.is(indexingLetter).countIn(w2.toString().toLowerCase());
            }
        });
        /** each word in list of words */
        for (Word word : words) {
            System.out.println(word);
        }
    }
}


