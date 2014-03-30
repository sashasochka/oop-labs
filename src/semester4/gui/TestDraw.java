package semester4.gui;

import semester4.CSVProcessor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import static javax.swing.JOptionPane.*;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TestDraw {

    private final JFrame frame;

    /**
     * Create the GUI and show it.
     */
    public TestDraw()  {
        // Make sure we have nice window decorations.
        setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        frame = new JFrame("CSV data diagram");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table instantiated using data from CSV file
        final ArrayList<ArrayList<Number>> data = getNumericDataFromFile(new File("numbers.csv"));
        final JTable table = new JTable(new CSVTableModel(data));
        // don't show talbe header
        table.setTableHeader(null);
        // make table scrollable
        final JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        final DiagramDrawer diagram = new DiagramDrawer(data);


        final JButton saveScreenshotBtn = new JButton("Save screenshot");
        saveScreenshotBtn.addActionListener(evt -> {
            try {
                diagram.saveTo(new File("screenshot.jpeg"), "jpeg");
            } catch (NullPointerException e) {
                // NPE is thrown instead of FileNotFoundException (or another IOException)
                // because of a bug in Oracle JDK
                warn("Can't save image to file!");
            } catch (IOException e) {
                // this exception never actually thrown because of a bug in Oracle JDK
                assert(false);
            }
        });

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.LINE_START);
        panel.add(diagram, BorderLayout.LINE_END);
        panel.add(saveScreenshotBtn, BorderLayout.PAGE_END);

        frame.getContentPane().add(panel);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Start application
     * @param args Command line args
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        invokeLater(TestDraw::new);
    }

    /**
     * Show dialog box with error message <code>errMsg</code> and print this message to <code>System.err</code>
     * After that finish the program with -1 error code
     * @param errMsg Error message
     */
    private void fatalError(final String errMsg) {
        System.err.println(errMsg);
        showMessageDialog(frame,
                errMsg,
                "Fatal error",
                ERROR_MESSAGE);
        exit(1);
    }

    /**
     * Show dialog box with error message from exception <code>e</code>,
     * print this message and stacktrace to <code>System.err</code>
     * After that finish the program with -1 error code
     * @param cause Cause of the error
     */
    private void fatalError(final Exception cause) {
        System.err.println(cause.getMessage());
        cause.printStackTrace();
        showMessageDialog(frame,
                cause.getMessage(),
                "Fatal error",
                ERROR_MESSAGE);
        exit(1);
    }


    /**
     * Show dialog box with warning message <code>errMsg</code> and print this message to <code>System.err</code>
     * @param warningMsg Error message
     */
    private void warn(final String warningMsg) {
        System.err.println(warningMsg);
        showMessageDialog(frame,
                warningMsg,
                "Warning",
                WARNING_MESSAGE);
    }

    /**
     * Show dialog box with warning message from exception <code>e</code>,
     * print this message and stacktrace to <code>System.err</code>
     * @param cause Cause of the error
     */
    private void warn(final Exception cause) {
        warn(cause.getMessage());
    }

    /**
     * Parses and returns 2d ArrayList with numeric data from CSV File <code>source</code>
     * @param source CSV file
     * @return 2d array with data
     */
    private ArrayList<ArrayList<Number>> getNumericDataFromFile(File source) {
        CSVProcessor csvProcessor = new CSVProcessor();
        try {
            csvProcessor.load(source);
            final ArrayList<ArrayList<String>> strData = csvProcessor.parse();
            final ArrayList<ArrayList<Number>> numData = new ArrayList<>(strData.size());
            for (int i = 0; i < strData.size(); i++) {
                numData.add(new ArrayList<>(strData.get(i).size()));
                for (int j = 0; j < strData.get(i).size(); j++) {
                    numData.get(i).add(NumberFormat.getInstance().parse(strData.get(i).get(j)));
                }
            }
            return numData;
        } catch (Exception e) {
            fatalError(e);
        }
        return null;
    }

    /**
     * TableModel for table with csv data
     */
    private class CSVTableModel extends AbstractTableModel {
        private final ArrayList<ArrayList<Number>> csvData;

        public CSVTableModel(final ArrayList<ArrayList<Number>> csvData) {
            this.csvData = csvData;
        }

        @Override
        public int getRowCount() {
            return csvData.size();
        }

        @Override
        public int getColumnCount() {
            return csvData.get(0).size();
        }

        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            return csvData.get(rowIndex).get(columnIndex);
        }

        @Override
        public boolean isCellEditable(final int rowIndex, final int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(final Object strValue, final int rowIndex, final int columnIndex) {
            try {
                final Number number = NumberFormat.getInstance().parse((String) strValue);
                csvData.get(rowIndex).set(columnIndex, number);
                frame.repaint();
            } catch (ParseException e) {
                warn(e);
            }
        }
    }
}
