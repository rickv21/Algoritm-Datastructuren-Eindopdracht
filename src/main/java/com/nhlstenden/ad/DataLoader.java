package com.nhlstenden.ad;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public List<Student> loadStudents(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("data.csv");

        List<Student> students = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> rows = reader.readAll();
            rows.remove(0); // Remove header row

            for (String[] row : rows) {
                int id = Integer.parseInt(row[0]);
                int studentNumber = Integer.parseInt(row[1]);
                String firstName = row[2];
                String lastName = row[3];
                int age = Integer.parseInt(row[4]);

                Student student = new Student(id, studentNumber, firstName, lastName, age);
                System.out.println("Loading: " + student);
                students.add(student);
            }

            // Do something with the list of people
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
