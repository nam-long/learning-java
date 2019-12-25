package learning.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Employee {

    public static void main(String[] args) {

        String filename = "resources/employee.json";
        String jsonString = readJsonFromFile(filename);

        System.out.println(jsonString);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(jsonString);

            System.out.println("Name: " + jsonObject.get("name"));
            System.out.println("Age: " + jsonObject.get("age"));
            System.out.println("Married: " + jsonObject.get("isMarried"));

            System.out.println("Hobbies:");
            JSONArray hobbies = (JSONArray) jsonObject.get("hobbies");
            for (int i = 0; i < hobbies.size(); i++) {
                String hobby = (String) hobbies.get(i);
                System.out.println("\t- " + hobby);
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
