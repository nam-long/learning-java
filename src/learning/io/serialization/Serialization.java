package learning.io.serialization;

import java.io.*;

public class Serialization {

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

    public void save(String filename) {
    }

    public void load(String filename) {
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