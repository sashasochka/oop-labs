package semester4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CSVProcessorTest {
    ArrayList<ArrayList<String>> toArrayList(String[][] data) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (String[] record: data) {
            result.add(new ArrayList<String>());
            for (String s: record) {
                result.get(result.size() - 1).add(s);
            }
        }
        return result;
    }

    ArrayList<ArrayList<String>> getParsedDataFromFile(String file) throws IOException {
        CSVProcessor csvProcessor = new CSVProcessor();
        csvProcessor.load(new File(file));
        return csvProcessor.parse();
    }

    @Test
    public void testSimple() throws IOException {
        String[][] data = {
            {"a","b","c","d","e","f"},
            {"1","2","3","4","5","6"},
            {"everything","should","work","fine","ok","here"}
        };
        assertEquals(getParsedDataFromFile("simple.csv"), toArrayList(data));
    }

    @Test
    public void testWithQuotes() throws IOException {
        String[][] data = {
                {"a\"b", "hello", " test"},
                {"one", "two", "three"}
        };
        assertEquals(toArrayList(data), getParsedDataFromFile("with-quotes.csv"));
    }

    @Test
    public void testComplex() throws IOException {
        String[][] data = {
                {"Year", "Make", "Model", "Description","Price"},
                {"1997", "Ford", "E350", "ac, abs, moon", "3000.00"},
                {"1999", "Chevy", "Venture \"Extended Edition\"","", "4900.00"},
                {"1999", "Chevy", "Venture \"Extended Edition, Very Large\"", "", "5000.00"},
                {"1996", "Jeep", "Grand Cherokee", "MUST SELL!\nair, moon roof, loaded", "4799.00"}
        };
        assertEquals(toArrayList(data), getParsedDataFromFile("complex.csv"));
    }

    @Test(expected = CSVParseException.class)
    public void testBadQuote() throws IOException {
        getParsedDataFromFile("bad-quote.csv");
    }

    @Test(expected = CSVParseException.class)
    public void testTooFewFields() throws IOException {
        getParsedDataFromFile("too-few-fields.csv");
    }

    @Test(expected = CSVParseException.class)
    public void testTooManyFields() throws IOException {
        getParsedDataFromFile("too-many-fields.csv");
    }
}
