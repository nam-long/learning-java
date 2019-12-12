package learning.io.noserialization;

import java.io.*;

public class NoSerialization {

    public static void main(String[] args) throws IOException {

        Student student = new Student("A", 20, "TP HCM");

        student.save("D:\\student.txt");

        student.load("D:\\student.txt");

        System.out.println(student);
    }
}

class Student {

    private String mName;
    private int mAge;
    private String mAddress;

    public Student(String name, int age, String address) {
        mName = name;
        mAge = age;
        mAddress = address;
    }

    public void save(String filename) throws IOException {

        // int - 4 bytes: lưu độ dài byte[] của mName
        // int - 4 bytes: lưu độ dài mảng byte[] mAge
        // int - 4 bytes: lưu độ dài mảng byte[] mAddress
        // lưu mảng byte[] của mName
        // lưu mảng byte[] của mAge
        // lưu mảng byte[] của mAddress

        FileOutputStream fos = new FileOutputStream(filename);
        DataOutputStream dos = new DataOutputStream(fos);

        int length = mName.getBytes("UTF8").length;
        dos.writeInt(length);
        dos.writeInt(4);
        length = mAddress.getBytes("UTF8").length;
        dos.writeInt(mAddress.getBytes("UTF8").length);

        dos.write(mName.getBytes("UTF8"));
        dos.writeInt(mAge);
        dos.write(mAddress.getBytes("UTF8"));

        fos.close();
    }

    public void load(String filename) throws IOException {

        FileInputStream fis = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(fis);

        int lengthOfName = dis.readInt();
        int lengthOfAge = dis.readInt();
        int lengthOfAddress = dis.readInt();

        byte[] buffer = new byte[Math.max(lengthOfName, lengthOfAddress)];
        dis.read(buffer, 0, lengthOfName);
        mName = new String(buffer, 0, lengthOfName, "UTF-8");

        mAge = dis.readInt();

        dis.read(buffer, 0, lengthOfAddress);
        mAddress = new String(buffer, 0, lengthOfAddress, "UTF-8");

        fis.close();
    }

    @Override
    public String toString() {
        return "Student{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                ", mAddress='" + mAddress + '\'' +
                '}';
    }
}