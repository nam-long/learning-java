package learning.swing.table.customized.movingdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        String[] columnNames = {"ID", "Full Name", "Salary"};

        modelAvailableEmployees = new EmployeesTableModel(columnNames);
        modelAvailableEmployees.addEmployee(new Employee(0, "Messi", 500000));
        modelAvailableEmployees.addEmployee(new Employee(2, "Ronaldo", 600000));
        modelAvailableEmployees.addEmployee(new Employee(3, "Bale", 400000));
        modelAvailableEmployees.addEmployee(new Employee(4, "Rooney", 300000));
        tableAvailableEmployees.setModel(modelAvailableEmployees);

        modelTeamMembers = new EmployeesTableModel(columnNames);
        tableTeamMembers.setModel(modelTeamMembers);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rowIndexes = tableAvailableEmployees.getSelectedRows();
                List<Employee> removedEmployees = modelAvailableEmployees.removeEmployee(rowIndexes);
                modelTeamMembers.addEmployees(removedEmployees);
            }
        });

        deselectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rowIndexes = tableTeamMembers.getSelectedRows();
                List<Employee> removedEmployees = modelTeamMembers.removeEmployee(rowIndexes);
                modelAvailableEmployees.addEmployees(removedEmployees);
            }
        });
    }

    public static void main(String[] args) {

        SetupTeam frame = new SetupTeam();
        frame.setTitle("Setup Team");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
