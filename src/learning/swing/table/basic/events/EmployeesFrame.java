package learning.swing.table.basic.events;

import javax.swing.*;
import java.awt.*;

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
    }

    public static void main(String[] args) {

        EmployeesFrame frame = new EmployeesFrame();
        frame.setTitle("Employees");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
