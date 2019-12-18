package learning.sqlite.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

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



    private void printStackTrace(String message) {

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 2; i < elements.length; i++) {
            System.out.println(String.format("[%s] %s", message, elements[i]));
        }
    }
}
