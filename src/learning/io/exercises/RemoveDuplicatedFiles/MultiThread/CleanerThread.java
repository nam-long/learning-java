package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CleanerThread {

    private Consumer mConsumer;

    private File mFile;

    private Thread mThread;
    private boolean mIsRunning;

    public CleanerThread(Consumer consumer, String filename) {

        mConsumer = consumer;
        mFile = new File(filename);
    }

    public void start() {

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {

                mIsRunning = true;

                while (mIsRunning || !mConsumer.isEmpty()) {
                    try {
                        File f = mConsumer.consume();
                        if (compareFileContent(f, mFile)) {
                            System.out.println("Deleting: " + f.getAbsolutePath());
                            f.delete();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        stop();
                    }
                }

                System.out.println("Cleaner finished.");
            }
        });
        mThread.start();
    }

    public void stop() {

        mIsRunning = false;

        if (mConsumer.isEmpty()) {
            mThread.interrupt();
        }
    }

    private boolean compareFileContent(File f1, File f2) {

        boolean result = true;

        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        int count1;
        int count2;
        byte[] buffer1 = new byte[100];
        byte[] buffer2 = new byte[100];

        try {
            fis1 = new FileInputStream(f1);
            fis2 = new FileInputStream(f2);
            while (result && (count1 = fis1.read(buffer1)) != -1 && (count2 = fis2.read(buffer2)) != -1) {
                if (count1 != count2) {
                    result = false;
                    break;
                } else {
                    for (int i = 0; i < count1; i++) {
                        if (buffer1[i] != buffer2[i]) {
                            result = false;
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        } finally {
            try {
                if (fis1 != null)
                    fis1.close();
                if (fis2 != null)
                    fis2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
