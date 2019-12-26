package learning.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import learning.json.Employee;
import learning.json.FileIO;

public class Jackson {

    public static void main(String[] args) {

        final String filename = "resources/employee.json";

        final String jsonString = FileIO.readText(filename);

        System.out.println("Read from file:\n" + jsonString);

        Employee employee = load(jsonString);
        System.out.println("Employee loaded:\n" + employee.toString());

//        employee.setAge(19);

        save(filename, employee);
    }

    public static Employee load(String jsonString) {

        ObjectMapper mapper = new ObjectMapper();

        Employee employee = null;
        try {
            employee = mapper.readValue(jsonString, Employee.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void save(String filename, Employee employee) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
            FileIO.writeText(filename, jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
