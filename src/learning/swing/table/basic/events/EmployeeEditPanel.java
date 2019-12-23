package learning.swing.table.basic.events;

import javax.swing.*;

public class EmployeeEditPanel {

    private JPanel rootPanel;
    private JTextField idTextField;
    private JTextField fullnameTextField;
    private JTextField salaryTextField;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public String getId() {
        return idTextField.getText();
    }

    public void setId(String id) {
        idTextField.setText(id);
    }

    public String getFullname() {
        return fullnameTextField.getText();
    }

    public void setFullname(String fullname) {
        fullnameTextField.setText(fullname);
    }

    public String getSalary() {
        return salaryTextField.getText();
    }

    public void setSalary(String salary) {
        salaryTextField.setText(salary);
    }
}
