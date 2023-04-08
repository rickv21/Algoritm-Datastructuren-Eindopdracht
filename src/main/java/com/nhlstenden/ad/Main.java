package com.nhlstenden.ad;

import com.nhlstenden.ad.data.CircularBuffer;
import com.nhlstenden.ad.data.CustomCollection;
import com.nhlstenden.ad.data.treemap.TreeMap;
import com.nhlstenden.ad.searching.Searcher;
import com.nhlstenden.ad.searching.SequentialSearch;
import com.nhlstenden.ad.sorting.BubbleSorter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        //Contains the student objects.
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 2, "test", "test", 20));
        students.add(new Student(3, 2322, "hello", "world", 15));

        CircularBuffer<Student> circularBuffer = new CircularBuffer<>(5);
        TreeMap<Integer, Student> treeMap = new TreeMap<>();

        List<Integer> knownNumbers = new ArrayList<>();
        for(Student student : students){
            //Max 255 entries, can always be increased.
            if(knownNumbers.size() >= 255){
                throw new ArrayIndexOutOfBoundsException("The max amount of students is 255.");
            }
            //Generate key for treemap, do not allow duplicates.
            int key = 0;
            while(knownNumbers.contains(key)){
                key = ThreadLocalRandom.current().nextInt(0, 255);
            }
            knownNumbers.add(key);
            circularBuffer.add(student);

            treeMap.add(treeMap.root, key, student);
        }


        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("Algoritme & Datastructuren");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Circular Buffer", null, generateTabContent(circularBuffer, "Circular Buffer"));
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Linked List", null, generateTabContent(new CircularBuffer<>(5), "Linked List"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Tree Map", null, generateTabContent(treeMap, "Tree Map"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel generateTabContent(CustomCollection<Student> collection, String title){
        JPanel contentPanel = new JPanel(new GridLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(24f));
        panel.add(titleLabel);

        JLabel size = new JLabel("Size :" + collection.getSize());
        panel.add(size);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton importButton = new JButton("Import dataset");
        panel.add(importButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        DefaultListModel<String> l1 = new DefaultListModel<>();
        JLabel sortSpeedLabel = new JLabel();

        if(title.equals("Tree Map")){
            JButton sort = new JButton("Balance");
            sort.addActionListener(e -> {
                balanceMap((TreeMap<Integer, Student>) collection, sortSpeedLabel, l1);
            });
            panel.add(new JLabel("Balancing:"));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(sort);
        } else {
            panel.add(new JLabel("Bubble Sort:"));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton firstNameSort = new JButton("Sort by first name");
            firstNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getFirstName), sortSpeedLabel, l1);
            });
            panel.add(firstNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton lastNameSort = new JButton("Sort by last name");
            lastNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getLastName),sortSpeedLabel, l1);
            });
            panel.add(lastNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton ageSort = new JButton("Sort by age");
            ageSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getAge),sortSpeedLabel, l1);
            });
            panel.add(ageSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton studentNumberSort = new JButton("Sort by student number");
            studentNumberSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getStudentNumber),sortSpeedLabel, l1);
            });
            panel.add(studentNumberSort);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(new JLabel("Searching:"));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel resultLabel = new JLabel("Result: ");
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        JTextField jTextField = new JTextField();
        jTextField.setSize(200, 20);
        jTextField.setMaximumSize(new Dimension(200, 20));
        inputPanel.add(jTextField);
        panel.add(inputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton sequentialSearch = new JButton("Sequential Search");
        sequentialSearch.addActionListener(e -> {
            SequentialSearch<String, Student> search = new SequentialSearch<>();
            Set<Student> students = search.search(jTextField.getText(), collection, Student::getFirstName);
            students.addAll(search.search(jTextField.getText(), collection, Student::getLastName));
            String output = Arrays.toString(students.toArray());
            System.out.println(output);
            resultLabel.setText("Result: " + (!students.isEmpty() ? output : "not found"));
        });
        panel.add(resultLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(sequentialSearch);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton search2 = new JButton("Binary Search");
        panel.add(search2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Speed:");
        speedPanel.add(label);
        speedPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        speedPanel.add(new JLabel(title.equals("Tree Map") ? "Balancing:" : "Bubble Sort:"));

        speedPanel.add(sortSpeedLabel);

        panel.add(speedPanel);

        contentPanel.add(panel);

        updateListModel(l1, collection.getStringArray());
        JList<String> list = new JList<>(l1);
        list.setBorder(new LineBorder(Color.BLACK));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(new JLabel("Collection contents:"), BorderLayout.NORTH);

        contentPanel.add(listPanel);

        return contentPanel;
    }

    private static void bubbleSort(CustomCollection<Student> collection, Comparator<Student> comparator, JLabel label, DefaultListModel<String> defaultListModel){
        BubbleSorter<Student> bubbleSorter = new BubbleSorter<>();
        long start = System.nanoTime();
        bubbleSorter.sort(collection, comparator);
        long end = System.nanoTime();
        label.setText((end - start) / 1000_000f + " ms ");
        System.out.println(label.getText());
        updateListModel(defaultListModel, collection.getStringArray());
    }

    private static void balanceMap(TreeMap<Integer, Student> treeMap, JLabel label, DefaultListModel<String> defaultListModel){
        long start = System.nanoTime();
        treeMap.balanceTree(treeMap.root);
        long end = System.nanoTime();
        label.setText((end - start) / 1000_000f + " ms ");
        System.out.println(label.getText());
        updateListModel(defaultListModel, treeMap.getStringArray());
    }

    private static void updateListModel(DefaultListModel<String> defaultListModel, String[] array){
        defaultListModel.clear();
        for(String s : array){
            defaultListModel.addElement(s);
        }
    }
}