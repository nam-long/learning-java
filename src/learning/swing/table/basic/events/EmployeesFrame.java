package learning.swing.table.basic.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeesFrame extends JFrame {

    private JPanel rootPanel;

    private JTable employeesTable;
    private EmployeesTableModel employeesTableModel;

    EmployeesFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        initComponents();
    }

    private void initComponents() {

        initEmployeesTable();

        employeesTableModel.addNewEmployee(new Object[] {"01", "Cafu", "3000"});
        employeesTableModel.addNewEmployee(new Object[] {"02", "Rivaldo", "6000"});
        employeesTableModel.addNewEmployee(new Object[] {"03", "Dida", "5000"});
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

    private void onEmployeesTableClicked(MouseEvent e) {

        int rowIndex = employeesTable.rowAtPoint(e.getPoint());
        if (rowIndex != -1) {

            EmployeeEditPanel editPanel = new EmployeeEditPanel();
            editPanel.setId(employeesTableModel.getValueAt(rowIndex, 0).toString());
            editPanel.setFullname(employeesTableModel.getValueAt(rowIndex, 1).toString());
            editPanel.setSalary(employeesTableModel.getValueAt(rowIndex, 2).toString());

            int option = JOptionPane.showConfirmDialog(this,
                    editPanel.getRootPanel(),
                    "Edit Employee",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                Object[] employee = {editPanel.getId(), editPanel.getFullname(), editPanel.getSalary()};
                employeesTableModel.updateEmployee(rowIndex, employee);
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
