package learning.swing.table.basic.fixedrow;

import javax.swing.table.AbstractTableModel;

class EmployeesTableModel extends AbstractTableModel {

    private Object[][] tableData;

    private int rowCount;

    private int columnCount;

    private String[] columnNames;

    public EmployeesTableModel(int numOfRows, String[] columnNames) {

        this.rowCount = 0;
        this.columnCount = columnNames.length;
        this.tableData = new Object[numOfRows][columnNames.length];
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addNewRow(Object[] rowData) {

        int endRowIndex = rowCount;
        Object[] endRowData = tableData[endRowIndex];
        for (int column = 0; column < columnCount; column++) {
            endRowData[column] = rowData[column];
        }
        rowCount++;
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {

        for (int row = rowIndex; row < rowCount - 1; row++) {
            for (int column = 0; column < columnCount; column++) {
                tableData[row][column] = tableData[row + 1][column];
            }
        }

        for (int column = 0; column < columnCount; column++) {
            tableData[rowCount - 1][column] = null;
        }

        rowCount--;
        fireTableDataChanged();
    }
}
