package learning.sample;

import java.io.File;
import java.util.LinkedList;

public class ConsumerProducer {

    private LinkedList<File> mFiles = new LinkedList<>();

    private Object mLock = new Object();

    public void produce(File f) {

        synchronized (mLock) {
            mFiles.add(f);
            mLock.notify();
        }
    }

    public File consume() {

        File f = null;

        synchronized (mLock) {

            while (mFiles.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    mLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
}
