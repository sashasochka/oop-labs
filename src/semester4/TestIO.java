package semester4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * This class shows an example of CSVProcessor class usage
 */
public class TestIO {
    /**
     * The main method run after launching
     * @param args Program arguments (not used)
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final CSVProcessor csvProcessor = new CSVProcessor();
        if (hasSerializedVersion()) {
            System.out.println("Loading serialized.dat...");
            csvProcessor.deserialize();
        } else {
            File file = getUserFile();
            if (file != null) {
                csvProcessor.load(file);
            } else {
                System.out.println("Exit...");
                System.exit(1);
            }
        }
        System.out.println("Serializing to serialized.dat...");
        csvProcessor.serialize();
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<ArrayList<ArrayList<String>>> future = es.submit(new Callable<ArrayList<ArrayList<String>>>() {
            @Override
            public ArrayList<ArrayList<String>> call() throws Exception {
                return csvProcessor.parse();
            }
        });
        System.out.println(future.get());
    }

    /**
     * Checks if there is a serialized file with data
     * @return boolean value if there is a file
     */
    private static boolean hasSerializedVersion() {
        return new File("serialized.dat").canRead();
    }

    /**
     * Ask user for a file location
     * @return User-specified file (as a File object)
     * @throws IOException
     */
    private static File getUserFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean success = false;
            int tries = 4;
            for (int i = 0; i < tries && !success; ++i) {
                try {
                    System.out.println("Enter the name of CSV file: ");
                    final String filename = br.readLine();
                    File file = new File(filename);
                    if (file.exists()) {
                        success = true;
                        System.out.println("Loading " + filename + "...");
                        return file;
                    }
                } catch (IOException e) {
                    if (i == tries) {
                        throw e;
                    }
                }
            }
        }
        return null;
    }
}
