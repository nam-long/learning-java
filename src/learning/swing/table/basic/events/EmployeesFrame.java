package learning.swing.table.basic.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeesFrame extends JFrame {

    private JPanel rootPanel;

    private JTable employeesTable;
    private JCheckBox editCheckbox;
    private EmployeesTableModel employeesTableModel;

    EmployeesFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        initComponents();
    }

    private void initComponents() {

        initEmployeesTable();

        /**
         * Thêm các nhân viên vào bảng
         */
        employeesTableModel.addNewEmployee(new Object[]{"01", "Cafu", "3000"});
        employeesTableModel.addNewEmployee(new Object[]{"02", "Rivaldo", "6000"});
        employeesTableModel.addNewEmployee(new Object[]{"03", "Dida", "5000"});
    }

    private void initEmployeesTable() {

        String[] columnNames = {"ID", "Full Name", "Salary"};
        employeesTableModel = new EmployeesTableModel(columnNames);
        employeesTable.setModel(employeesTableModel);
        employeesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onEmployeesTableClicked(e);
            }
        });
    }

    // Xử lý double-click trên một dòng của bảng Employees
    private void onEmployeesTableClicked(MouseEvent e) {

        // Kiểm tra check-box có được chọn nay không?
        if (!editCheckbox.isSelected())
            return;

        // Nếu check-box đã được chọn, kiểm tra tiếp double-click có xảy ra hay không?
        if (e.getClickCount() > 1) {

            // Lấy chỉ số dòng từ vị trí double-clicked
            int rowIndex = employeesTable.rowAtPoint(e.getPoint());

            // Kiểm tra chỉ số dòng có hợp lệ?
            if (rowIndex != -1) {
                /**
                 * Tạo một JPanel chứa các thành phần giao diện để hiển thị thông tin Employee,
                 * cho phép nhập liệu để thay đổi những thông tin này.
                 */
                EmployeeEditPanel editPanel = new EmployeeEditPanel();
                /**
                 * Lấy thông tin từ dòng được chọn trên bảng lần lượt gán vào các thành phần
                 * hiển thị tương ứng trên dialog
                 */
                editPanel.setId(employeesTableModel.getValueAt(rowIndex, 0).toString());
                editPanel.setFullname(employeesTableModel.getValueAt(rowIndex, 1).toString());
                editPanel.setSalary(employeesTableModel.getValueAt(rowIndex, 2).toString());

                /**
                 * Mở dialog hiển thị thông tin nhân viên, cho phép thay đổi những thông tin này,
                 * và đợi kết quả trả về. Kết quả được trả về khi người dùng nhấn nút OK trên Dialog.
                 * Trường hợp nút Cancel được nhấn, chương trình sẽ không làm gì cả.
                 */
                int option = JOptionPane.showConfirmDialog(this,
                        editPanel.getRootPanel(),
                        "Edit Employee",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                // Xử lý khi nút Yes trên Dialog được bấm
                if (option == JOptionPane.YES_OPTION) {

                    // Tạo một mảng để lưu thông tin nhân viên được lấy về từ dialog
                    Object[] employee = {editPanel.getId(), editPanel.getFullname(), editPanel.getSalary()};

                    // Cập nhật thông tin của nhân viên thông qua TableModel của bảng Employees
                    employeesTableModel.updateEmployee(rowIndex, employee);
                }
            }
        }
    }

    public static void main(String[] args) {

        EmployeesFrame frame = new EmployeesFrame();
        frame.setTitle("Employees");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
