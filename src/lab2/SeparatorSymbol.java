package lab2;

public class SeparatorSymbol extends Symbol {
    private static String separators = ".!? \n\r\t";
    static public boolean isSeparator(char symbol) {
        return separators.contains(Character.toString(symbol));
    }

    public SeparatorSymbol(char symbol) {
        super(symbol);
    }
}
