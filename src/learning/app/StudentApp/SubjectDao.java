package learning.app.StudentApp;

import java.util.List;

public interface SubjectDao {

    void create(Subject subject);

    Subject getById(int id);

    List<Subject> getAllSubjects();

    void update(Subject subject);

    void delete(int id);
}
