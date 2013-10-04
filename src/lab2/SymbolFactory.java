package lab2;

public class SymbolFactory {
    public static Symbol charToSymbol(char character) {
        if (SeparatorSymbol.isSeparator(character)) {
            return new SeparatorSymbol(character);
        } else {
            return new Symbol(character);
        }
    }
}
