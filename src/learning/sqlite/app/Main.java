package learning.sqlite.app;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        String sql;

        SQLite sqlite = SQLite.getInstance();

        sqlite.open("sample.db");

        sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL\n"
                + ");";
        sqlite.newTable(sql);

        sqlite.close();
    }
}
