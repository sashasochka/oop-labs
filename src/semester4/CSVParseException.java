package semester4;

public class CSVParseException extends RuntimeException {
    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param err           the detail message
     */
    public CSVParseException(final String err, final int row, final int col) {
        super(err + " at line " + row + ", column " + col);
    }
}
