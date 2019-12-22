package learning.swing.table.basic.events;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

class EmployeesTableModel extends AbstractTableModel {

    private final String[] columnNames;

    private List<Object[]> employees = new Vector();

    public EmployeesTableModel(String[] columnNames) {
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

        Object[] employee = employees.get(rowIndex);
        return employee[columnIndex];
    }

    public void addNewEmployee(Object[] employee) {

        employees.add(employee);
        fireTableDataChanged();
    }
}
