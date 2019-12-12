package learning.io;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class RemoteFile {

    public static void main(String[] args) throws IOException {

        String str1 = downloadResource("https://raw.githubusercontent.com/nam-long/learning-java/master/resources/cadao.txt");
        System.out.println("File content:\n" + str1);
    }

    /**
     * Đọc nội dung của tập tin
     * @param strUrl
     * @return Nội dung của tập tin kiểu chuỗi
     * @throws IOException
     */
    public static String downloadResource(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[100];
        int count; /* số bytes đọc được và ghi vào buffer */
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        str = new String(baos.toByteArray());

        is.close();

        return str;
    }

    /**
     * Tải tập tin
     *
     * @param strUrl Đường dẫn url tới tập tin cần tải
     * @param filename Tên tập tin được lưu trên máy tính
     * @throws IOException
     */
    public static void downloadResource(String strUrl, String filename) throws IOException {

        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fos = new FileOutputStream(filename);

        byte[] buffer = new byte[100];
        int count; /* Số bytes được đọc và ghi vào buffer */
        while ((count = bis.read(buffer)) != -1) {

            // Dùng đối tượng FileOutputStream để ghi buffer vừa đọc được vào file
            fos.write(buffer, 0, count);
        }

        fos.close();
        is.close();
    }

    /**
     * Đọc nội dung tập tin trên internet
     *
     * @param strUrl Đường dẫn liên kết đến tập tin trên internet
     * @return Nội dung tập tin dạng chuỗi
     * @throws IOException
     */
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

    /**
     * Tải tập tin ảnh và lưu xuống máy tính
     *
     * @param imageUrl Đường dẫn liên kết đến tập tin ảnh trên internet
     * @param filename Tên tập tin được lưu trên máy tin
     * @throws IOException
     */
    public static void downloadImage(String imageUrl, String filename) throws IOException {

        byte[] imageData = downloadImageData(imageUrl);
        saveFile(filename, imageData);
    }

    /**
     * Lưu tập tin trên máy tin
     * @param filename Tên tập tin được lưu trên máy
     * @param data Nội dung tập tin
     * @throws IOException
     */
    public static void saveFile(String filename, byte[] data) throws IOException {

        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }

    /**
     * Đọc nội dung một tập tin ảnh theo mảng bytes
     * @param strUrl Đường dẫn liên kết tới tập tin trên internet
     * @return Nội dung tập tin dạng mảng bytes
     * @throws IOException
     */
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
}
