package learning.swing.table.customized.movingdata;

class Employee {

    private int id;

    private String fullname;

    private float salary;

    public Employee(int id, String fullname, float salary) {
        this.id = id;
        this.fullname = fullname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
