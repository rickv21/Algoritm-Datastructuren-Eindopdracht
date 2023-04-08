package com.nhlstenden.ad;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public List<Student> loadStudents(){
        List<Student> students = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            List<String[]> rows = reader.readAll();
            rows.remove(0); // Remove header row

            for (String[] row : rows) {
                int id = Integer.parseInt(row[0]);
                int studentNumber = Integer.parseInt(row[1]);
                String firstName = row[2];
                String lastName = row[3];
                int age = Integer.parseInt(row[4]);

                Student student = new Student(id, studentNumber, firstName, lastName, age);
                students.add(student);
            }

            // Do something with the list of people
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
