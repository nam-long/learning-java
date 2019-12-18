package learning.sqlite.app;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

        String sql;

        SQLite sqlite = SQLite.getInstance();

        sqlite.open("sample.db");

        sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL\n"
                + ");";
        sqlite.newTable(sql);

//        sql = "INSERT INTO student(name) VALUES(?)";
//        sqlite.insert(sql,"Beto");

//        sql = "UPDATE student SET name = ? WHERE id = ?";
//        sqlite.update(sql, 2, "Kadic");

//        sql = "DELETE FROM student WHERE id = ?";
//        sqlite.delete(sql, 5);

        sql = "SELECT id, name FROM student";
        sqlite.select(sql);

        sqlite.close();
    }
}
