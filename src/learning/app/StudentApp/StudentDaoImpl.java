package learning.app.StudentApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final String SQL_CREATE_STUDENT =
            "INSERT INTO StudentTable(Fullname,DayOfBirth,CityAddress,Gender) " +
                    "VALUES(?,?,?,?);";

    private static final String SQL_GET_STUDENT_BY_ID = "SELECT * FROM StudentTable WHERE Id = ?;";

    private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM StudentTable;";

    private static final String SQL_UPDATE_STUDENT =
            "UPDATE StudentTable " +
                    "SET FullName = ?, DayOfBirth = ?, CityAddress = ?, Gender = ? " +
                    "WHERE Id = ?;";

    private static final String SQL_DELETE_STUDENT = "DELETE FROM StudentTable WHERE Id = ?;";

    private Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Student student) {

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFullname());
            ps.setString(2, student.getDayOfBirth());
            ps.setString(3, student.getCityAddress());
            ps.setString(4, student.getGender());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
                System.out.println("Inserted: " + student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getById(int id) {

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_GET_STUDENT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setFullname(rs.getString(2));
                student.setDayOfBirth(rs.getString(3));
                student.setCityAddress(rs.getString(4));
                student.setGender(rs.getString(5));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_STUDENTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setFullname(rs.getString(2));
                student.setDayOfBirth(rs.getString(3));
                student.setCityAddress(rs.getString(4));
                student.setGender(rs.getString(5));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_STUDENT);
            ps.setString(1, student.getFullname());
            ps.setString(2, student.getDayOfBirth());
            ps.setString(3, student.getCityAddress());
            ps.setString(4, student.getGender());
            ps.setInt(5, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_STUDENT);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
