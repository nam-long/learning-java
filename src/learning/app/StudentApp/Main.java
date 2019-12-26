package learning.app.StudentApp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        Database db = new Database("students.db");

        Connection connection = db.getConnection();

        db.initiate();

        SubjectDao subjectDao = new SubjectDaoImpl(connection);
//        subjectDao.create(Subject.newInstance("English"));
//        subjectDao.create(Subject.newInstance("Math"));
//        subjectDao.create(Subject.newInstance("Java"));

//        System.out.println(subjectDao.getById(1));

//        Subject obj = subjectDao.getById(1);
//        obj.setName("C/C++");
//        subjectDao.update(obj);

//        subjectDao.delete(2);

        List<Subject> subjects = subjectDao.getAllSubjects();
        for (Subject subject: subjects) {
            System.out.println(subject);
        }

        StudentDao studentDao = new StudentDaoImpl(connection);
//        studentDao.create(Student.newInstance("Nesta", "1980", "Milan", "Male"));
//        studentDao.create(Student.newInstance("Del Pierro", "1982", "Juventus", "Male"));
//        studentDao.create(Student.newInstance("Alexandra", "1988", "Bucharest", "Female"));
//        studentDao.create(Student.newInstance("Alan Shearer", "1980", "Newcastle", "Male"));

//        System.out.println(studentDao.getById(2));

//        Student studentObj = studentDao.getById(2);
//        studentObj.setDayOfBirth("1981");
//        studentObj.setCityAddress("Galaxy");
//        studentDao.update(studentObj);

//        studentDao.delete(4);

        List<Student> students = studentDao.getAllStudents();
        for (Student student: students) {
            System.out.println(student);
        }

        db.close();
    }
}
