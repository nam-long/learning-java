package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;

public class ScannerThread {

    private Producer mProducer;

    private File mFolder;

    private File mFile;

    private OnCompletionListener mOnCompletionListener;

    public ScannerThread(Producer producer, String folder, String filename, OnCompletionListener onCompletionListener) {

        mProducer = producer;
        mFolder = new File(folder);
        mFile = new File(filename);
        mOnCompletionListener = onCompletionListener;
    }

    public void start() {

        Thread thread = new Thread(new Runnable() {
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

                System.out.println("Scanner finished.");
            }
        });
        thread.start();
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
                if (f.length() == mFile.length() && f.getName().equalsIgnoreCase(mFile.getName())) {
                    mProducer.produce(f);
                    System.out.println("Find: " + f.getAbsolutePath());
                }
            }
        }
    }
}
