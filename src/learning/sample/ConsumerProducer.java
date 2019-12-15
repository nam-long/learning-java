package learning.sample;

import java.io.File;
import java.util.LinkedList;

public class ConsumerProducer {

    private LinkedList<File> mFiles = new LinkedList<>();

    private Object mLock = new Object();

    public void produce(File f) {

        synchronized (mLock) {
            mFiles.add(f);
            System.out.println(Thread.currentThread().getName() + " notify. Produce: " + f.getAbsolutePath());
            mLock.notify();
        }
    }

    public File consume() {

        File f = null;

        synchronized (mLock) {

            while (!Scanner.isFinished() && mFiles.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    mLock.wait();
                    System.out.println(Thread.currentThread().getName() + " notified!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!mFiles.isEmpty()) {
                f = mFiles.removeFirst();
                System.out.println(Thread.currentThread().getName() + " notify. Consume: " + f.getAbsolutePath() + ". Size-list: " + mFiles.size());
                mLock.notify();
            }
        }

        return f;
    }

    public boolean isEmpty() {

        synchronized (mLock) {
            return mFiles.isEmpty();
        }
    }

    public void unlockAll() {
        synchronized (mLock) {
            mLock.notifyAll();
        }
    }
}
