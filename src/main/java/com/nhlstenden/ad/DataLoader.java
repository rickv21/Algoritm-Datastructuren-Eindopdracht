package com.nhlstenden.ad;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public List<Student> loadStudents(){
        List<Student> students = new ArrayList<>();
        File file;
        //InputStream is = getClass().getClassLoader().getResourceAsStream("data.csv");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return "CSV Files (*.csv)";
            }
        });
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            // process selectedFile
        } else {
            return students;
        }


        try (CSVReader reader = new CSVReader(new FileReader(file))) {
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
            JOptionPane.showMessageDialog(null, "Dit is geen geldig Student CSV bestand!", "CSV load error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return students;
    }
}
