package learning.swing.table.basic.fixedrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeesFrame extends JFrame {

    private JPanel rootPanel;

    private JTextField idTextField;
    private JTextField fullnameTextField;
    private JTextField salaryTextField;

    private JButton addButton;
    private JButton removeButton;

    private JTable employeesTable;
    private EmployeesTableModel employeesTableModel;

    EmployeesFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        initComponents();
    }

    private void initComponents() {

        initEmployeesTable();
        initButtons();
    }

    private void initEmployeesTable() {

        final int NUMBER_OF_ROWS = 10;
        final String[] columnNames = {"ID", "Full Name", "Salary"};
        employeesTableModel = new EmployeesTableModel(NUMBER_OF_ROWS, columnNames);

        employeesTable.setModel(employeesTableModel);
    }

    private void initButtons() {

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = idTextField.getText();
                String fullname = fullnameTextField.getText();
                String salary = salaryTextField.getText();

                Object[] rowData = {id, fullname, salary};

                employeesTableModel.addNewRow(rowData);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRowIndex = employeesTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    employeesTableModel.removeRow(selectedRowIndex);
                }
            }
        });
    }

    public static void main(String[] args) {

        EmployeesFrame frame = new EmployeesFrame();
        frame.setTitle("Employees");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
