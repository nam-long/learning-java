package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;

public class ScannerThread {

    private ConsumerProducer mConsumerProducer;

    private File mFolder;

    private File mFile;

    private OnCompletionListener mOnCompletionListener;

    private Thread mThread;
    private String mThreadName;

    private int mCount;

    public ScannerThread(String threadName, ConsumerProducer consumerProducer, String folder, String filename, OnCompletionListener onCompletionListener) {

        mThreadName = threadName;
        mConsumerProducer = consumerProducer;
        mFolder = new File(folder);
        mFile = new File(filename);
        mOnCompletionListener = onCompletionListener;
    }

    public void execute() {

        if (mThread != null) {
            throw new IllegalStateException(Thread.currentThread().getName() + " already created!");
        }

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    scanFile(mFolder);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mOnCompletionListener != null) {
                    mOnCompletionListener.onComplete();
                }

                System.out.println(Thread.currentThread().getName() + " FINISHED. Number of files found is: " + mCount);
            }
        }, mThreadName);
        mThread.start();
    }

    private void scanFile(File folder) throws InterruptedException {

        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        for (File f: files) {
            if (f.isDirectory()) {
                scanFile(f);
            } else {
                if (f.length() == mFile.length() && f.getName().equalsIgnoreCase(mFile.getName()) && !f.equals(mFile)) {
                    mConsumerProducer.produce(f);
                    mCount++;
                    System.out.println(Thread.currentThread().getName() + " found: " + f.getAbsolutePath());
                }
            }
        }
    }
}
