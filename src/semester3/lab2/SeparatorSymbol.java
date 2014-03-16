package semester3.lab2;

public class SeparatorSymbol extends Symbol implements SentencePart {
    /** List of sentence separators */
    private static final String sentenceSeparators = ".!?";
    /** List of separator symbols (includes all sentence separators) */
    private static final String separators = sentenceSeparators + ",#@";

    /** Test if \a symbol is a valid character to a SeparatorSymbol initializer character */
    static public boolean charIsSeparator(char symbol) {
        return separators.contains(Character.toString(symbol));
    }

    /** Derive constructor from base class */
    public SeparatorSymbol(char symbol) {
        super(symbol);
    }

    /** Check if this separator separates sentences */
    public final boolean isSentenceSeparator() {
        return sentenceSeparators.contains(Character.toString(getChar()));
    }

    @Override
    public boolean isWord() {
        return false;
    }
}
