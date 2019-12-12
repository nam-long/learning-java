package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

public class Main {

    public static void main(String[] args) {

        String folder = "D:\\Java-nang-cao\\learning-java\\test";
        String filename = "D:\\Java-nang-cao\\learning-java\\abc.txt";

        ConsumerProducer cp = new ConsumerProducer();

        CleanerThread cleanerThread = new CleanerThread(cp, filename);
        cleanerThread.start();

        ScannerThread scannerThread = new ScannerThread(cp, folder, filename, new OnCompletionListener() {
            @Override
            public void onComplete() {
                cleanerThread.stop();
            }
        });
        scannerThread.start();
    }
}
