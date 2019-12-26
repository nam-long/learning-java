package learning.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import learning.json.Employee;
import learning.json.FileIO;

public class GSON {

    public static void main(String[] args) {

        final String filename = "resources/employee.json";

        final String jsonString = FileIO.readText(filename);

        System.out.println(jsonString);

        Employee employee = load(jsonString);
        System.out.println("Load: " + employee.toString());

        employee.setAge(32);

        save(filename, employee);
    }

    public static Employee load(String jsonString) {

        Gson gson = new Gson();
        Employee employee = gson.fromJson(jsonString, Employee.class);
        return employee;
    }

    public static void save(String filename, Employee employee) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(employee);
        FileIO.writeText(filename, jsonString);
    }
}
