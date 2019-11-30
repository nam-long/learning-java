package learning.io;

import java.io.*;

public class ReadLocalFile {

    public static void main(String[] args) throws IOException {

        String str1 = readByLine("D:\\Java-nang-cao\\learning-java\\resources\\ca-dao.txt");
        System.out.println(str1);

        String str2 = readUTF8ByLine("D:\\Java-nang-cao\\learning-java\\resources\\cadao.txt");
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

    public static String readByLine(String filename) throws IOException {

        String str = "";

        InputStream is = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(is);
        String line;
        while ((line = dis.readLine()) != null) {
            str += line + '\n';
        }
        is.close();

        return str;
    }

    public static String readUTF8ByLine(String filename) throws IOException {

        String str = "";

        Reader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            str += line + '\n';
        }
        reader.close();

        return str;
    }
}
