package learning.swing.table;

import javax.swing.table.AbstractTableModel;

public class BasicTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"ID", "Full Name", "Salary"};

    private static final int MAX_ROW = 100;

    private Object[][] data = new Object[MAX_ROW][COLUMN_NAMES.length];

    private int rowCount = 0;

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public void addRow(Object[] rowData) {

        for (int column = 0; column < COLUMN_NAMES.length; column++) {
            data[rowCount][column] = rowData[column];
        }
        rowCount++;
        fireTableDataChanged();
    }
}
