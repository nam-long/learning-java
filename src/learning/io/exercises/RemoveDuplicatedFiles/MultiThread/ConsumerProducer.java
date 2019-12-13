package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;
import java.util.LinkedList;

public class ConsumerProducer {

    private static final int LIMIT = 3;

    private LinkedList<File> mFiles = new LinkedList<>();

    private final Object mLock = new Object();

    public File consume() throws InterruptedException {

        File f = null;
        synchronized (mLock) {

            while (mFiles.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                mLock.wait();
            }

            f = mFiles.removeFirst();

            mLock.notify();
        }
        return f;
    }

    public boolean isEmpty() {

        synchronized (mLock) {
            return mFiles.isEmpty();
        }
    }

    public void produce(File f) throws InterruptedException {

        synchronized (mLock) {

            while (mFiles.size() == (LIMIT - 1)) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                mLock.wait();
            }

            mFiles.add(f);

            mLock.notify();
        }
    }
}
