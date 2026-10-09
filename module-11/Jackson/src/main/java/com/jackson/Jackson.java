package com.jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 *
 * Marco Rodriguez
 * 10/8/2026
 * Module 11.2 
 */
public class Jackson {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Student student = new Student(1, "Marco Rodriguez", "Web Development");

            String jsonString = mapper.writeValueAsString(student);
            System.out.println("Serialized JSON Output:");
            System.out.println(jsonString);

            Student parsedStudent = mapper.readValue(jsonString, Student.class);
            System.out.println("Deserialized Java Object:");
            System.out.println("Name: " + parsedStudent.getName());
            System.out.println("Major: " + parsedStudent.getMajor());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private int id;
    private String name;
    private String major;

    public Student() {
    }

    public Student(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
