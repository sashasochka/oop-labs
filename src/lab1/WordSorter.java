package lab1;

import com.google.common.base.CharMatcher;

import java.io.InputStream;
import java.util.*;

public class WordSorter {
    public static void main(String[] args) {
        // Letter for sorting words by number of its occurences
        final Character indexLetter = Character.toLowerCase(parseIndexLetterOption(args));
        // list of words in text
        List<StringBuffer> words = getWords(System.in);
        // Sort words by indexLetter occurences
        Collections.sort(words, new Comparator<StringBuffer>() {
            @Override
            public int compare(final StringBuffer s1, final StringBuffer s2) {
                return CharMatcher.is(indexLetter).countIn(s1.toString().toLowerCase()) -
                        CharMatcher.is(indexLetter).countIn(s2.toString().toLowerCase());
            }
        });
        // print words in sorted order
        for (StringBuffer word : words) {
            System.out.println(word);
        }
    }

    private static char parseIndexLetterOption(String[] args) {
        // check if command line arguments is correct
        // should by only one 1-letter argument
        if (args.length != 1 || args[0].length() != 1) {
            System.err.println("Format: [jar-name] S\n" +
                    "\twhere L is a letter for word sorting. Words will be sorted\n" +
                    "\tby number of contained symbols S in increasing order ");
            System.exit(1);
        }
        // symbol for sorting words by number of its occurences
        final char indexingLetter = args[0].charAt(0);
        // check that its a letter
        if (!Character.isLetter(indexingLetter)) {
            System.err.println(indexingLetter + " is not a letter!");
            System.exit(2);
        }
        return indexingLetter;
    }

    private static List<StringBuffer> getWords(InputStream stream) {
        // list of words read from stream
        List<StringBuffer> words = new ArrayList<>();
        final Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            final StringBuffer word = new StringBuffer(
                    scanner.next().replaceAll("[!?\\\\\"\'.,]", ""));
            words.add(word);
        }
        return words;
    }
}
