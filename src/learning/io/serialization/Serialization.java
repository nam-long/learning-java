package learning.io.serialization;

import java.io.*;

public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student student = new Student("A", 20, "TP HCM");

        student.save("D:\\student.txt");

        Student student1 = student.load("D:\\student.txt");

        System.out.println(student1);
    }
}

class Student implements Serializable {

    private String mName;
    private int mAge;
    private String mAddress;

    public Student(String name, int age, String address) {
        mName = name;
        mAge = age;
        mAddress = address;
    }

    public void save(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        fos.close();
    }

    public Student load(String filename) throws IOException, ClassNotFoundException {
        Student student;

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        student = (Student) ois.readObject();
        fis.close();

        return student;
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