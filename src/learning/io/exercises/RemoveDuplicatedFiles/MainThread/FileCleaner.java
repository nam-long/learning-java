package learning.io.exercises.RemoveDuplicatedFiles.MainThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCleaner {

    public static void main(String[] args) {

        String folder = "test/duplicated-files";
        String filename = "test/duplicated-files/abc.txt";
        FileCleaner helper = new FileCleaner(folder, filename);
        helper.removeDuplicatedFiles();
    }

    private File mFolder;

    private File mFile;

    private List<File> mFiles = new ArrayList<>();

    public FileCleaner(String folder, String filename) {
        mFolder = new File(folder);
        mFile = new File(filename);
    }

    public void removeDuplicatedFiles() {

        mFiles.clear();

        listFiles(mFolder);

        if (mFiles.size() > 1) {
            mFiles.remove(0);
            for (File f: mFiles) {
                System.out.println("Deleting file: " + f.getAbsolutePath());
                f.delete();
            }
            mFiles.clear();
        }
    }

    private void listFiles(File folder) {

        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        for (File f: files) {
            if (f.isDirectory()) {
                listFiles(f);
            } else {
                if (f.length() == mFile.length() && f.getName().equalsIgnoreCase(mFile.getName()) && compareFileContent(f, mFile)) {
                    mFiles.add(f);
                    System.out.println("List: " + f.getAbsolutePath());
                }
            }
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

                        // Increase cost of comparing files
                        try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }

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
