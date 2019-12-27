package learning.app.studentmanager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Database {

    private static final String TAG = "Database";

    private Connection mConnection;

    public Database(String filePath) throws SQLException {

        File dbFile = new File(filePath);

        final String url = "jdbc:sqlite:" + filePath;
        mConnection = DriverManager.getConnection(url);

        if (dbFile.exists()) {
            Debug.i(TAG, String.format("'%s' opened", dbFile.getName()));
        } else {
            Debug.i(TAG, String.format("'%s' created", dbFile.getName()));
        }
    }

    public Connection getConnection() {
        return mConnection;
    }

    public void close() throws SQLException {

        mConnection.close();
        Debug.i(TAG, String.format("Closed"));
    }

    public void initiate() throws SQLException {

        createSubjectTable();
        createStudentTable();
        createMarkTable();
    }

    private void createSubjectTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SubjectTable (\n"
                + "    Id integer PRIMARY KEY,\n"
                + "    Name text NOT NULL\n"
                + ");";
    createTable(sql);
}

    private void createStudentTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS StudentTable (\n"
                + "    Id integer PRIMARY KEY,\n"
                + "    FullName text NOT NULL,\n"
                + "    DayOfBirth text NOT NULL,\n"
                + "    CityAddress text NOT NULL,\n"
                + "    Gender text NOT NULL\n"
                + ");";
        createTable(sql);
    }

    private void createMarkTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS MarkTable (\n"
                + "    SubjectId integer,\n"
                + "    StudentId integer,\n"
                + "    Mark real,\n"
                + "    Notes text,\n"
                + "    PRIMARY KEY (SubjectId, StudentId),\n"
                + "    FOREIGN KEY (SubjectId) REFERENCES SubjectTable (id),\n"
                + "    FOREIGN KEY (StudentId) REFERENCES StudentTable (id)\n"
                + ");";
        createTable(sql);
    }

    private void createTable(String sql) throws SQLException {

        Statement statement = mConnection.createStatement();
        statement.execute(sql);
    }
}
