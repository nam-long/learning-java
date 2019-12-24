package learning.swing.table.customized.movingdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SetupTeam extends JFrame {

    private JPanel rootPanel;

    private JButton deselectButton;
    private JButton selectButton;

    private JTable tableAvailableEmployees;
    private EmployeesTableModel modelAvailableEmployees;

    private JTable tableTeamMembers;
    private EmployeesTableModel modelTeamMembers;

    SetupTeam() {

        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        initTables();

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Chỉ số của những dòng đang được chọn trong bảng.
                 *
                 * Lưu ý: với cách cài đặt danh sách (List) cho dữ liệu của bảng,
                 * thì thứ tự các phần tử trong danh sách cũng tương ứng với thứ tự
                 * của các phần tử trong bảng.
                 */
                int[] rowIndexes = tableAvailableEmployees.getSelectedRows();
                if (rowIndexes.length > 0) {
                    onSelect(rowIndexes);
                }
            }
        });

        deselectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Chỉ số của những dòng đang được chọn trong bảng.
                 *
                 * Lưu ý: với cách cài đặt danh sách (List) cho dữ liệu của bảng,
                 * thì thứ tự các phần tử trong danh sách cũng tương ứng với thứ tự
                 * của các phần tử trong bảng.
                 */
                int[] rowIndexes = tableTeamMembers.getSelectedRows();
                if (rowIndexes.length > 0) {
                    onDeselect(rowIndexes);
                }
            }
        });
    }

    private void initTables() {

        String[] columnNames = {"ID", "Full Name", "Salary"};

        modelAvailableEmployees = new EmployeesTableModel(columnNames);
        tableAvailableEmployees.setModel(modelAvailableEmployees);
        tableAvailableEmployees.addMouseListener(new MouseAdapter() {

            /**
             * Cài đặt sự kiện Double-Clicked: employee được chọn sẽ di chuyển sang
             * bảng Team Members.
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                // Đếm số lần click
                if (e.getClickCount() > 1) {
                    // Xác định dòng đang được chọn từ tọa độ của một điểm (Point) trên bảng
                    int rowIndex = tableAvailableEmployees.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        int[] rowIndexes = {rowIndex};
                        onSelect(rowIndexes);
                    }
                }
            }
        });

        modelTeamMembers = new EmployeesTableModel(columnNames);
        tableTeamMembers.setModel(modelTeamMembers);
        tableTeamMembers.addMouseListener(new MouseAdapter() {

            /**
             * Cài đặt sự kiện Double-Clicked: team member được chọn sẽ di chuyển sang
             * bảng Available Employees.
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                // Đếm số lần click
                if (e.getClickCount() > 1) {
                    // Xác định dòng đang được chọn từ tọa độ của một điểm (Point) trên bảng
                    int rowIndex = tableTeamMembers.rowAtPoint(e.getPoint());
                    if (rowIndex != -1) {
                        int[] rowIndexes = {rowIndex};
                        onDeselect(rowIndexes);
                    }
                }
            }
        });

        loadAvailableEmployees();
    }

    private void loadAvailableEmployees() {
        modelAvailableEmployees.addEmployee(new Employee(0, "Messi", 500000));
        modelAvailableEmployees.addEmployee(new Employee(2, "Ronaldo", 600000));
        modelAvailableEmployees.addEmployee(new Employee(3, "Bale", 400000));
        modelAvailableEmployees.addEmployee(new Employee(4, "Rooney", 300000));
    }

    private void onSelect(int[] rowIndexes) {
        List<Employee> removedEmployees = modelAvailableEmployees.removeEmployee(rowIndexes);
        modelTeamMembers.addEmployees(removedEmployees);
    }

    private void onDeselect(int[] rowIndexes) {
        List<Employee> removedEmployees = modelTeamMembers.removeEmployee(rowIndexes);
        modelAvailableEmployees.addEmployees(removedEmployees);
    }

    public static void main(String[] args) {

        SetupTeam frame = new SetupTeam();
        frame.setTitle("Setup Team");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
