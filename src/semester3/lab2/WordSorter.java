package semester3.lab2;

import java.util.Collections;
import java.util.List;

public class WordSorter {
    public static void main(String[] args) {
        /** this letter for sorting words by number of its occurences */
        final Symbol indexingLetter = new Symbol(Character.toLowerCase('o'));
        /** string which contains words to be sorted */
        final String textString = "Hello, how are you? Ooo oooo Oo    oo you!";

        /** Text object containing OO representation of text string */
        Text text = new Text(textString);
        List<Word> words = text.getWords();
        /** Sort words by number of indexingLetter occurences */
        /** use custom Comparator */
        Collections.sort(words, (w1, w2) -> w1.countLetter(indexingLetter) - w2.countLetter(indexingLetter));
        /** each word in list of words */
        words.forEach(System.out::println);
    }
}


