package learning.swing.table;

import javax.swing.*;
import java.awt.*;

public class BasicTable extends JFrame {

    private JPanel rootPanel;
    private JTextField txtID;
    private JTextField txtFullName;
    private JTextField txtSalary;
    private JButton addButton;
    private JTable table;

    public BasicTable() {

        setTitle("Basic Table");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(800, 600));
        pack();

        BasicTableModel model = new BasicTableModel();
        table.setModel(model);

        model.addRow(new String[]{"01", "ABC", "1000"});
    }

    public static void main(String[] args) {

        BasicTable frame = new BasicTable();

        frame.setVisible(true);
    }
}
