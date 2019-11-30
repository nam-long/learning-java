package learning.io;

import java.io.*;

public class ReadLocalFile {

    public static void main(String[] args) throws IOException {

        String str1 = read("D:\\Java-nang-cao\\learning-java\\resources\\cadao.txt");
        System.out.println(str1);

        String str2 = readUTF8("D:\\Java-nang-cao\\learning-java\\resources\\cadao.txt");
        System.out.println(str2);
    }

    public static String read(String filename) throws IOException {

        String str = "";

        InputStream is = new FileInputStream(filename);
        int c;
        while ((c = is.read()) != -1) {
            str += (char) c;
        }

        is.close();
        return str;
    }

    public static String readUTF8(String filename) throws IOException {

        String str = "";

        Reader is = new FileReader(filename);
        int c;
        while ((c = is.read()) != -1) {
            str += (char) c;
        }

        is.close();
        return str;
    }
}
