package lab2;

import java.util.ArrayList;
import java.util.List;

public class WordSorter {
    public static void main(String[] args) {
        /** the letter for sorting words by number of its occurences */
        final char indexingLetter = Character.toLowerCase('o');
        /** string which contains words to be sorted */
        final StringBuffer textString = new StringBuffer("Hello, how are you? Ooo oooo Oo    oo " +
                "you!");

        /** check that indexingLetter is a letter */
        if (!Character.isLetter(indexingLetter)) {
            System.err.println(indexingLetter + " is not a letter!");
            System.exit(1);
        }
        List<Symbol> symbols = new ArrayList<>();
        Text text = new Text();
        Sentence curSentence = new Sentence();
        Word curWord = new Word();
        for (char c : textString.toString().toCharArray()) {
            Symbol symbol = SymbolFactory.charToSymbol(c);
        }

//
//        /** list of words in textString */
//        List<StringBuffer> words = new ArrayList<>();
//        /** Use default scanner for splitting words by white-space characters */
//        final Scanner scanner = new Scanner(textString.toString());
//        while (scanner.hasNext()) {
//            /** next word from textString */
//            final StringBuffer word = new StringBuffer(
//                    scanner.next().replaceAll("[!?\\\\\"\'.,]", ""));
//            words.add(word);
//        }
//
//        /** Sort words by number of indexingLetter */
//        /** use custom Comparator */
//        Collections.sort(words, new Comparator<StringBuffer>() {
//            /**
//             *
//             * @param s1 first string to compare
//             * @param s2 second string to compare
//             * @return
//             */
//            @Override
//            public int compare(final StringBuffer s1, final StringBuffer s2) {
//                return CharMatcher.is(indexingLetter).countIn(s1.toString().toLowerCase()) -
//                        CharMatcher.is(indexingLetter).countIn(s2.toString().toLowerCase());
//            }
//        });
//        /** each word in words */
//        for (StringBuffer word : words) {
//            System.out.println(word);
//        }
    }
}


