package com.nhlstenden.ad;

import com.nhlstenden.ad.data.CircularBuffer;
import com.nhlstenden.ad.data.CustomCollection;
import com.nhlstenden.ad.sorting.BubbleSorter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("Algoritme & Datastructuren");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Circular Buffer", null, generateTabContent(new CircularBuffer<>(5), "Circular Buffer"));
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Linked List", null, generateTabContent(new CircularBuffer<>(5), "Linked List"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Tree Map", null, generateTabContent(new CircularBuffer<>(5), "Tree Map"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel generateTabContent(CustomCollection<Student> collection, String title){
        collection.add(new Student(1, 2, "test", "test", 20));
        collection.add(new Student(3, 2322, "hello", "world", 15));

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
        JLabel bubbleSortLabel = new JLabel();

        if(title.equals("Tree Map")){
            JButton sort = new JButton("Balance");
            panel.add(new JLabel("Balancing:"));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(sort);
        } else {
            panel.add(new JLabel("Bubble Sort:"));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton firstNameSort = new JButton("Sort by first name");
            firstNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getFirstName), bubbleSortLabel, l1);
            });
            panel.add(firstNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton lastNameSort = new JButton("Sort by last name");
            lastNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getLastName),bubbleSortLabel, l1);
            });
            panel.add(lastNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton ageSort = new JButton("Sort by age");
            ageSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getAge),bubbleSortLabel, l1);
            });
            panel.add(ageSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton studentNumberSort = new JButton("Sort by student number");
            studentNumberSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getStudentNumber),bubbleSortLabel, l1);
            });
            panel.add(studentNumberSort);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(new JLabel("Searching:"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton search1 = new JButton("Sequential Search");
        panel.add(search1);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton search2 = new JButton("Binary Search");
        panel.add(search2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Speed:");
        speedPanel.add(label);
        speedPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        speedPanel.add(new JLabel("Bubble Sort:"));

        speedPanel.add(bubbleSortLabel);

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
        //bubbleSortLabel.paintImmediately(bubbleSortLabel.getVisibleRect());
        System.out.println(label.getText());
        updateListModel(defaultListModel, collection.getStringArray());
    }

    private static void updateListModel(DefaultListModel<String> defaultListModel, String[] array){
        defaultListModel.clear();
        for(String s : array){
            defaultListModel.addElement(s);
        }
    }
}