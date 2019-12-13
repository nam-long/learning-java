package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String folder = "test/duplicated-files";
        String filename = "test/duplicated-files/abc.txt";

        ConsumerProducer cp = new ConsumerProducer();

        CleanerThread cleanerThread = new CleanerThread("Cleaner-00", cp, filename);
        cleanerThread.execute();

        ScannerThread scannerThread = new ScannerThread("Scanner-00", cp, folder, filename, new OnCompletionListener() {
            @Override
            public void onComplete() {
                cleanerThread.stop();
            }
        });
        scannerThread.execute();
    }
}
