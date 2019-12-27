package learning.mvc.studentmanager;

public class Subject {

    private int id;

    private String name;

    public static Subject newInstance(String name) {

        Subject subject = new Subject();
        subject.setName(name);
        return subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "mId=" + id +
                ", mName='" + name + '\'' +
                '}';
    }
}
