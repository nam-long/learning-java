package learning.json.simple;

import learning.json.Employee;
import learning.json.FileIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Simple {

    public static void main(String[] args) {

        final String filename = "resources/employee.json";

        final String jsonString = FileIO.readText(filename);

        System.out.println(jsonString);

        Employee employee = load(jsonString);
        System.out.println("Load: " + employee.toString());

        // modify something
        employee.setAge(30);
        // save
        save(employee, filename);
    }

    public static Employee load(String jsonString) {

        Employee employee = new Employee();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            // Tạo một đối tượng JSON từ một chuỗi
            jsonObject = (JSONObject) parser.parse(jsonString);

            employee.setName((String) jsonObject.get(Employee.NAME));
            employee.setAge(Integer.parseInt(jsonObject.get(Employee.AGE).toString()));
            employee.setMarried((Boolean) jsonObject.get(Employee.MARRIED));

            /**
             * Lấy đối tượng json kiểu mảng có tên là "hobbies".
             * Lưu ý: Trong trường hợp này, đối tượng mảng "hobbies" chỉ chứa giá trị đơn giản,
             * không chứa đối tượng có cấu trúc phức tạp, nên chỉ cần duyệt các phần tử trong
             * đối tượng kiểu mảng này để có thể lấy ra giá trị của nó.
             */
            JSONArray hobbies = (JSONArray) jsonObject.get(Employee.HOBBIES);
            for (int i = 0; i < hobbies.size(); i++) {
                String hobby = (String) hobbies.get(i);
                employee.addHobby(hobby);
            }

            /**
             * Khác với "hobbies", đối tượng kiểu mảng "kids" chứa các phần tử có kiểu đối tượng
             * phức tạp (name, age), các phần tử này cần phải được xử ly như một đối tượng json
             * (JSONObject)
             */
            JSONArray kids = (JSONArray) jsonObject.get(Employee.KIDS);
            for (int i = 0; i < kids.size(); i++) {
                JSONObject kidJsonObject = (JSONObject) kids.get(i);
                String name = (String) kidJsonObject.get(Employee.KID_NAME);
                int age = Integer.parseInt(kidJsonObject.get(Employee.KID_AGE).toString());

                Employee.Kid kid = new Employee.Kid(name, age);
                employee.addKid(kid);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void save(Employee employee, String filename) {

        JSONObject employeeObject = new JSONObject();

        employeeObject.put(Employee.NAME, employee.getName());
        employeeObject.put(Employee.AGE, employee.getAge());
        employeeObject.put(Employee.MARRIED, employee.isMarried());

        JSONArray hobbies = new JSONArray();
        hobbies.addAll(employee.getHobbies());
        employeeObject.put(Employee.HOBBIES, employee.getHobbies());

        JSONArray kids = new JSONArray();
        for (Employee.Kid kid: employee.getKids()) {

            JSONObject kidObject = new JSONObject();
            kidObject.put(Employee.KID_NAME, kid.getName());
            kidObject.put(Employee.KID_AGE, kid.getAge());

            kids.add(kidObject);
        }
        employeeObject.put(Employee.KIDS, kids);

        String jsonString = employeeObject.toJSONString();
        FileIO.writeText(filename, jsonString);
    }
}
