package learning.app.studentmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

    private static final String SQL_CREATE_SUBJECT = "INSERT INTO SubjectTable(name) VALUES(?);";

    private static final String SQL_GET_SUBJECT_BY_ID = "SELECT * FROM SubjectTable WHERE Id = ?;";

    private static final String SQL_GET_ALL_SUBJECTS = "SELECT * FROM SubjectTable;";

    private static final String SQL_UPDATE_SUBJECT = "UPDATE SubjectTable SET name = ? WHERE Id = ?;";

    private static final String SQL_DELETE_SUBJECT = "DELETE FROM SubjectTable WHERE Id = ?;";

    private Connection mConnection;

    public SubjectDaoImpl(Connection connection) {
        mConnection = connection;
    }

    @Override
    public void create(Subject subject) {
        try {
            PreparedStatement preparedStatement = mConnection.prepareStatement(SQL_CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                subject.setId(rs.getInt(1));
                System.out.println("Create a Subject: id = " + subject.getId() + ", name = " + subject.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subject getById(int id) {

        try {
            PreparedStatement prepareStatement = mConnection.prepareStatement(SQL_GET_SUBJECT_BY_ID);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt(1));
                subject.setName(rs.getString(2));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = mConnection.prepareStatement(SQL_GET_ALL_SUBJECTS);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt(1));
                subject.setName(rs.getString(2));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public void update(Subject subject) {

        try {
            PreparedStatement preparedStatement = mConnection.prepareStatement(SQL_UPDATE_SUBJECT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try {
            PreparedStatement preparedStatement = mConnection.prepareStatement(SQL_DELETE_SUBJECT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
