package learning.io;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class ReadLocalFile {

    public static void main(String[] args) throws IOException {

//        String str1 = readBuffer("D:\\Java-nang-cao\\learning-java\\resources\\ca-dao.txt");
//        System.out.println(str1);
//
//        String str2 = readBuffer("D:\\Java-nang-cao\\learning-java\\resources\\cadao.txt");
//        System.out.println(str2);

        String str3 = downloadResource("https://raw.githubusercontent.com/nam-long/learning-java/master/resources/cadao.txt");
        System.out.println(str3);

//        downloadResource("https://raw.githubusercontent.com/nam-long/learning-java/master/resources/cadao.txt",
//                "D:\\test\\test.txt");

//        String imageUrl = "";
//        downloadImage(imageUrl, "D:\\test.png");

        listFiles("D:\\Java-nang-cao");
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

    public static String readBuffer(String filename) throws IOException {

        String str = null;
        byte[] buffer = new byte[10];

        InputStream is = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int count; /* So byte doc vao buffer */
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        str = new String(baos.toByteArray());
        baos.close();
        is.close();

        return str;
    }

    public static String downloadResourceByHttps(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count; /* So byte doc vao buffer */
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = new String(baos.toByteArray());

            is.close();
        }

        return str;
    }

    public static String downloadResource(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[100];
        int count; /* So byte doc vao buffer */
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
            ///
        }
        str = new String(baos.toByteArray());

        is.close();

        return str;
    }

    public static void downloadResource(String strUrl, String filename) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fos = new FileOutputStream(filename);

        byte[] buffer = new byte[100];
        int count; /* Số bytes được đọc vào buffer */
        while ((count = bis.read(buffer)) != -1) {

            // Dùng đối tượng FileOutputStream để ghi buffer vừa đọc được vào file
            fos.write(buffer, 0, count);
        }

        fos.close();
        is.close();
    }

    public static void downloadImage(String imageUrl, String filename) throws IOException {

        byte[] imageData = downloadImageData(imageUrl);
        saveFile(filename, imageData);
    }

    public static byte[] downloadImageData(String strUrl) throws IOException {

        byte[] imageData = null;

        URL url = new URL(strUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count; /* So byte doc vao buffer */
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            imageData = baos.toByteArray();

            is.close();
        }

        return imageData;
    }

    public static void saveFile(String filename, byte[] data) throws IOException {

        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }

    public static ArrayList<String> listPaths = new ArrayList<>();

    public static void listFiles(String folderPath) throws IOException {

        File folder = new File(folderPath);

        File[] files = folder.listFiles();
        if (files.length == 0) {
            listPaths.add(folder.getCanonicalPath());
            System.out.println(folder.getCanonicalPath());
        }

        for (File f : files) {
            if (f.isFile()) {
                listPaths.add(f.getCanonicalPath());
                System.out.println(f.getCanonicalPath());
            } else {
                listFiles(f.getPath());
            }
        }
    }

    /**
     * Tìm tập tin có tên là 'filename', xóa những tập tin khác giống
     * (trùng tên, giống nội dung) với tập tin này trong folderPath.
     *
     * Ví dụ: removeDuplicatedFiles("D:\\project", "readme.txt");
     *
     * @param folderPath
     * @param filename
     */
    public static void removeDuplicatedFiles(String folderPath, String filename) {
    }
}
