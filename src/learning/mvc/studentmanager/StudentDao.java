package learning.mvc.studentmanager;

import java.util.List;

public interface StudentDao {

    void create(Student student);

    Student getById(int id);

    List<Student> getAllStudents();

    void update(Student student);

    void delete(int id);
}
