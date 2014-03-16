package semester4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor {
    File defaultFile = new File("serialized.dat");
    List<String> lines;

    // state used during parsing
    private int curFieldIndex;
    private boolean firstLine;
    private boolean previousWasQuote;
    private boolean inQuotes;
    private int nFieldsPerRecord; // not known at beginning
    private ArrayList<String> record;
    private StringBuilder field;
    private int row;
    private int col;
    ArrayList<ArrayList<String>> data;



    public void load(File file) throws IOException {
        lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
    }

    public void save(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line: lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void serialize(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(lines);
        }
    }

    public void serialize() throws IOException {
        serialize(defaultFile);
    }

    public void deserialize(File file) throws IOException {
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file))) {
            try {
                // noinspection unchecked
                lines = (List<String>) oin.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void deserialize() throws IOException {
        deserialize(defaultFile);
    }

    ArrayList<ArrayList<String>> parse() {
        try {
            resetState();
            row = 1;
            for (String line: lines) {
                col = 1;
                for (char c: line.toCharArray()) {
                    initField();
                    initRecord();
                    parseSymbol(c);
                    col++;
                }
                parseSymbol('\n');
                row++;
            }
            if (inQuotes) {
                csvParseError("No closing quote found");
            }
        } catch (Exception e) {
            csvParseError("Unknown exception");
        }
        return data;
    }

    private void initRecord() {
        if (record == null) {
            record = new ArrayList<>();
            curFieldIndex = 1;
        }
    }

    private void initField() {
        if (field == null) {
            field = new StringBuilder();
        }
    }

    private void resetState() {
        data = new ArrayList<>();
        inQuotes = false;
        previousWasQuote = false;
        firstLine = true;
        nFieldsPerRecord = curFieldIndex = 1;
        record = null;
        field = null;
    }

    private void csvParseError(String msg) {
        throw new CSVParseException(msg, row, col);
    }

    private void addFieldToRecord() {
        if (field != null) {
            record.add(field.toString());
            field = null;
        }
    }

    private void addRecordToData() {
        addFieldToRecord();
        data.add(record);
        if (curFieldIndex < nFieldsPerRecord) {
            csvParseError("too few fields in the record");
        }
        field = null;
        record = null;
        firstLine = false;
    }

    private void updateFieldIndex() {
        curFieldIndex++;
        if (firstLine) {
            nFieldsPerRecord++;
        } else if (curFieldIndex > nFieldsPerRecord) {
            csvParseError("too many fields in the record");
        }
    }

    private void parseSymbol(char c) {
        if (previousWasQuote) {
            if (c == '"') {
                field.append('"');
                previousWasQuote = false;
                return;
            } else {
                if (inQuotes && c != ',' && c != '\n') {
                    csvParseError("unexpected symbol after closing quote: ");
                }
                inQuotes = !inQuotes;
            }
        }

        if (c == ',' && !inQuotes) {
            addFieldToRecord();
            updateFieldIndex();
            previousWasQuote = false;
        } else if (c == '"') {
            if (!previousWasQuote) {
                previousWasQuote = true;
            }
        } else if (c == '\n' && !inQuotes) {
            addRecordToData();
            previousWasQuote = false;
        } else {
            field.append(c);
            previousWasQuote = false;
        }
    }
}
