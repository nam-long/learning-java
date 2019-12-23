package learning.swing.table.customized.movingdata;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class EmployeesTableModel extends AbstractTableModel {

    private String[] columnNames;

    private List<Employee> employees = new Vector<>();

    EmployeesTableModel(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getFullname();
            default:
                return employee.getSalary();
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        fireTableDataChanged();
    }

    public void addEmployees(List<Employee> employees) {

        this.employees.addAll(employees);
        fireTableDataChanged();
    }

    public List<Employee> removeEmployee(int[] rowIndexes) {

        List<Employee> removedEmployees = new Vector<>();
        for (int rowIndex: rowIndexes) {
            removedEmployees.add(employees.get(rowIndex));
        }
        employees.removeAll(removedEmployees);
        fireTableDataChanged();
        return removedEmployees;
    }
}
