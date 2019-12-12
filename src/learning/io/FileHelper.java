package learning.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static void main(String[] args) {

        String folder = "D:\\Java-nang-cao\\learning-java\\test";
        String filename = "D:\\Java-nang-cao\\learning-java\\abc.txt";
        FileHelper helper = new FileHelper(folder, filename);
        helper.removeDuplicatedFiles();
    }

    private File mFolder;

    private File mFile;

    private List<File> mFiles = new ArrayList<>();

    public FileHelper(String folder, String filename) {
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
                if (f.length() == mFile.length() && f.getName().equalsIgnoreCase(mFile.getName())) {
                    mFiles.add(f);
                    System.out.println("List: " + f.getAbsolutePath());
                }
            }
        }
    }
}
