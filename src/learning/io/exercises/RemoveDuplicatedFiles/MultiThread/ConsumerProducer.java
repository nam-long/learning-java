package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;
import java.util.LinkedList;

public class ConsumerProducer implements Consumer, Producer {

    private static final int LIMIT = 2;

    private LinkedList<File> mFiles = new LinkedList<>();

    private Object mLock = new Object();

    @Override
    public File consume() throws InterruptedException {

        File f = null;
        synchronized (mLock) {

            while (mFiles.isEmpty()) {
                mLock.wait();
            }

            f = mFiles.removeFirst();

            mLock.notify();
        }
        return f;
    }

    @Override
    public boolean isEmpty() {

        synchronized (mLock) {
            return mFiles.isEmpty();
        }
    }

    @Override
    public void produce(File f) throws InterruptedException {

        synchronized (mLock) {

            while (mFiles.size() == LIMIT) {
                mLock.wait();
            }

            mFiles.add(f);

            mLock.notify();
        }
    }
}
