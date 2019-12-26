package learning.json;

import java.util.List;
import java.util.Vector;

public class Employee {

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String MARRIED = "isMarried";
    public static final String HOBBIES = "hobbies";
    public static final String KIDS = "kids";
    public static final String KID_NAME = "name";
    public static final String KID_AGE = "age";

    private String name;

    private int age;

    private boolean isMarried;

    private List<String> hobbies = new Vector<>();

    private List<Kid> kids = new Vector<>();

    public static class Kid {

        private String name;

        private int age;

        public Kid() {
        }

        public Kid(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Kid{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return isMarried;
    }

    /**
     * Phương thức này là cần thiết để làm việc với thư viện Jackson
     */
    public boolean getIsMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        this.isMarried = married;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public List<Kid> getKids() {
        return kids;
    }

    public void addKid(Kid kid) {
        kids.add(kid);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", married=" + isMarried +
                ", hobbies=" + hobbies +
                ", kids=" + kids +
                '}';
    }
}
