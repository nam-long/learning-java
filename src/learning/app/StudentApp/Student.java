package learning.app.StudentApp;

public class Student {

    private int id;
    private String fullname;
    private String dayOfBirth;
    private String cityAddress;
    private String gender;

    public static Student newInstance(String fullname, String dayOfBirth, String cityAddress, String gender) {

        Student student = new Student();
        student.setFullname(fullname);
        student.setDayOfBirth(dayOfBirth);
        student.setCityAddress(cityAddress);
        student.setGender(gender);
        return student;
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

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", cityAddress='" + cityAddress + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
