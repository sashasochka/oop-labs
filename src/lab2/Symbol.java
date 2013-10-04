package lab2;

public class Symbol {
    private char character;

    public Symbol(char character) {
        this.character = character;
    }

    public char getChar() {
        return character;
    }

    public String toString() {
        return Character.toString(character);
    }

    public boolean isWhitespace() {
        return Character.isWhitespace(getChar());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Symbol)) {
            return false;
        }

        final Symbol symbol = (Symbol) o;

        if (character != symbol.character) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) character;
    }

    public Symbol toLower() {
        return new Symbol(Character.toLowerCase(character));
    }
}
