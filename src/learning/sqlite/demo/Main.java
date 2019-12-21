package learning.sqlite.demo;

import java.sql.*;

/**
 * HƯỚNG DẪN THAO TÁC TRÊN CƠ SỞ DỮ LIỆU - SQLite
 *
 * Tham khảo: https://www.sqlitetutorial.net/sqlite-java/
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        /**
         * Đường dẫn đến tập tin cơ sở dữ liệu
         */
        String dbPath = "sample.db";

        final String url = "jdbc:sqlite:" + dbPath;

        /**
         * Tạo kết nối đến cơ sở dữ liệu tên là 'sample.db'.
         * Nếu tập tin cơ sở dữ liệu này chưa tồn tại thì cơ sở dữ liệu
         * sẽ được tạo mới.
         */
        Connection connection = DriverManager.getConnection(url);

        // Tạo bảng tên là 'Employee' thuộc cơ sở dữ liệu 'sample.db'
        createEmployeeTable(connection);

        /**
         * Thêm mới một nhân viên vào bảng 'Employee'.
         * Chú ý: trường ID của nhân viên có kiểu dữ liệu là integer
         * sẽ được tự động tăng lên mỗi khi thêm mới một nhân viên vào
         * bảng 'Employee'.
         */
//        insertEmployee(connection, "Nesta", 5000);

        /**
         * Thực hiện câu truy vấn đến cơ sở dữ liệu để lấy thông tin của
         * nhân viên.
         * Điều kiện của câu truy vấn là ID của nhân viên cần tìm.
         */
        selectEmployeeById(connection, 1);

        /**
         * Cập nhật thông tin của nhân viên
         */
        updateEmployeeById(connection, 2, "Ronaldo", 10000);

        /**
         * Xóa nhân viên ra khỏi cơ sở dữ liệu
         */
//        deleteEmployeeById(connection, 3);

        selectAllEmployees(connection);

        /**
         * Đóng kết nối đến cơ sở dữ liệu 'sample.db'
         */
        connection.close();
    }

    public static void createEmployeeTable(Connection connection) throws SQLException {

        String SQL_CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS EmployeeTable (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    fullname text NOT NULL,\n"
                + "    salary integer\n"
                + ");";
        Statement statement = connection.createStatement();
        statement.execute(SQL_CREATE_EMPLOYEE_TABLE);
    }

    public static void insertEmployee(Connection connection, String fullname, int salary) throws SQLException {

        final String SQL_CREATE_EMPLOYEE = "INSERT INTO EmployeeTable(fullname, salary)" +
                "VALUES(?,?)";

        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);

        /**
         * Gán giá trị thật sự cho các tham số (?) trong câu lệnh SQL.
         * Lưu ý: thứ tự các tham số bắt đầu từ 1 (không giống như kiểu dữ liệu mảng có thứ tự bắt đầu từ 0)
         * Đối với câu lệnh SQL_CREATE_EMPLOYEE, có 2 tham số (?,?) đại diện lần lượt cho fullname và salary.
         */
        ps.setString(1, fullname);
        ps.setInt(2, salary);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            System.out.println("Inserted id: " + id);
        }
    }

    public static void selectEmployeeById(Connection connection, int employeeId) throws SQLException {

        final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT * FROM EmployeeTable WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
        ps.setInt(1, employeeId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            String fullname = rs.getString(2);
            int salary = rs.getInt(3);
            System.out.println("ID: " + id + ", Full name: " + fullname + ", Salary: " + salary);
        }
    }

    public static void selectAllEmployees(Connection connection) throws SQLException {

        final String SQL_SELECT_ALL_EMPLOYEES = "SELECT * FROM EmployeeTable";

        /**
         * Chú ý: đối với những câu lệnh SQLite không cần truyền tham số (dấu ?) cho nó
         * thì có thể sử dụng Statement để thay cho PreparedStatement
         */
        Statement statement = connection.createStatement();

        /**
         * Kết quả của truy vấn sẽ là thông tin của nhân viên, thông tin này được quản lý
         * bởi đối tượng có kiểu ResultSet
         */
        ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_EMPLOYEES);

        /**
         * Vì đây là câu lệnh SELECT ALL, có kết quả trả về sẽ tất cả các nhân viên
         * trong bảng 'Employee', nên kết quả này có thể có nhiều dòng (record).
         * Do đó nhất nhiết phải dùng vòng lặp 'while' để duyệt cho từng kết quả.
         */
        while (rs.next()) {
            /**
             * Lưu ý: các giá trị 1, 2, 3 bên dưới lần lượt là thứ tự của các trường dữ liệu
             *  id, fullname, salary thuộc bảng dữ liệu 'Employee'
             */
            int id = rs.getInt(1);
            String fullname = rs.getString(2);
            int salary = rs.getInt(3);

            System.out.println("ID: " + id + ", Full name: " + fullname + ", Salary: " + salary);
        }
    }

    public static void updateEmployeeById(Connection connection, int id, String fullname, int salary) throws SQLException {

        final String SQL_UPDATE_EMPLOYEE_BY_ID = "UPDATE EmployeeTable SET Fullname = ?, Salary = ? WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_EMPLOYEE_BY_ID);
        ps.setString(1, fullname);
        ps.setInt(2, salary);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public static void deleteEmployeeById(Connection connection, int id) throws SQLException {

        final String SQL_DELETE_EMPLOYEE_BY_ID = "DELETE FROM EmployeeTable WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL_DELETE_EMPLOYEE_BY_ID);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
