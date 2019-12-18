package learning.sqlite.app;

import java.sql.*;

public class SQLite {

    private static class Singleton {
        private static final SQLite sInstance = new SQLite();
    }

    private Connection mConnection;

    private SQLite() {
    }

    public static SQLite getInstance() {
        return Singleton.sInstance;
    }

    public void open(String filePath) throws SQLException {

        final String url = "jdbc:sqlite:" + filePath;
        mConnection = DriverManager.getConnection(url);
        if (mConnection != null) {
            System.out.println("Open database.");
        }
    }

    public void close() throws SQLException {

        if (mConnection != null) {
            mConnection.close();
            System.out.println("Close database.");
        } else {
            printStackTrace("Connection is NULL");
        }
    }

    public boolean newTable(String sql) throws SQLException {

        if (mConnection != null) {
            Statement statement = mConnection.createStatement();
            boolean result = statement.execute(sql);
            System.out.println("New table: " + result);
            return true;
        } else {
            printStackTrace("Connection is NULL");
        }
        return false;
    }

    public void insert(String sql, String name) throws SQLException {

        if (mConnection != null) {
            PreparedStatement preparedStatement = mConnection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } else {
            printStackTrace("Connection is NULL");
        }
    }

    public void select(String sql) throws SQLException {

        if (mConnection != null) {
            Statement statement = mConnection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
            }
        } else {
            printStackTrace("Connection is NULL");
        }
    }

    public void update(String sql, int id, String newName) throws SQLException {

        if (mConnection != null) {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } else {
            printStackTrace("Connection is NULL");
        }
    }

    public void delete(String sql, int id) throws SQLException {

        if (mConnection != null) {
            PreparedStatement preparedStatement = mConnection.prepareStatement(sql);

            // set the corresponding param
            preparedStatement.setInt(1, id);
            // execute the delete statement
            preparedStatement.executeUpdate();
        } else {
            printStackTrace("Connection is NULL");
        }
    }

    private void printStackTrace(String message) {

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 2; i < elements.length; i++) {
            System.out.println(String.format("[%s] %s", message, elements[i]));
        }
    }
}
