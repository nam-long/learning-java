package learning.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Employee {

    public static void main(String[] args) {

        final String filename = "resources/employee.json";

        final String jsonString = readJsonFromFile(filename);

        System.out.println(jsonString);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            // Tạo một đối tượng JSON từ một chuỗi
            jsonObject = (JSONObject) parser.parse(jsonString);

            System.out.println("Name: " + jsonObject.get("name"));
            System.out.println("Age: " + jsonObject.get("age"));
            System.out.println("Married: " + jsonObject.get("isMarried"));

            /**
             * Lấy đối tượng json kiểu mảng có tên là "hobbies".
             * Lưu ý: Trong trường hợp này, đối tượng mảng "hobbies" chỉ chứa giá trị đơn giản,
             * không chứa đối tượng có cấu trúc phức tạp, nên chỉ cần duyệt các phần tử trong
             * đối tượng kiểu mảng này để có thể lấy ra giá trị của nó.
             */
            JSONArray hobbies = (JSONArray) jsonObject.get("hobbies");
            System.out.println("Hobbies:");
            for (int i = 0; i < hobbies.size(); i++) {
                String hobby = (String) hobbies.get(i);
                System.out.println("\t- " + hobby);
            }

            /**
             * Khác với "hobbies", đối tượng kiểu mảng "kids" chứa các phần tử có kiểu đối tượng
             * phức tạp (name, age), các phần tử này cần phải được xử ly như một đối tượng json
             * (JSONObject)
             */
            JSONArray kids = (JSONArray) jsonObject.get("kids");
            System.out.println("Kids:");
            for (int i = 0; i < kids.size(); i++) {
                JSONObject kid = (JSONObject) kids.get(i);
                String name = (String) kid.get("name");
                long age = (Long) kid.get("age");
                System.out.println(String.format("\tKid %d - name: %s, age: %d", (i + 1), name, age));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFromFile(String filename) {

        String jsonString = "";
        String line;
        BufferedReader dis = null;
        try {
            dis = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            while ((line = dis.readLine()) != null) {
                jsonString += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null)
                    dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }
}
