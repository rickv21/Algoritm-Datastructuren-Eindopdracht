package com.nhlstenden.ad;

import com.nhlstenden.ad.data.CircularBuffer;
import com.nhlstenden.ad.data.CustomCollection;
import com.nhlstenden.ad.data.treemap.TreeMap;
import com.nhlstenden.ad.linkedlist.LinkedList;
import com.nhlstenden.ad.searching.SequentialSearch;
import com.nhlstenden.ad.sorting.BubbleSorter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UITab extends JPanel {

    private final JButton clearButton = new JButton("Clear data");
    private final JButton importButton = new JButton("Import dataset");
    private final JLabel sizeLabel = new JLabel("Size: 0");
    private final JLabel sortSpeedLabel = new JLabel("Speed: -ms");
    private final JLabel sequentialSearchSpeedLabel = new JLabel("Speed: -ms");
    private final JLabel binarySearchSpeedLabel = new JLabel("Speed: -ms");
    private final DefaultListModel<String> resultModel = new DefaultListModel<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final CustomCollection<Student> collection;

    public UITab(CustomCollection<Student> collection, String title){
        this.collection = collection;
        generateTab(collection, title);
    }

    public void generateTab(CustomCollection<Student> collection, String title){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // set the column
        gbc.gridy = 0; // set the row
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        sizeLabel.setText("Size: " + collection.getSize());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(24f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
      //  addElement(panel, titleLabel);
        addElement(panel, sizeLabel);
        clearButton.addActionListener(e -> {
            collection.clear();
            listModel.clear();
            resultModel.clear();
            sizeLabel.setText("Size: 0");
        });
        importButton.addActionListener(e -> {
            loadDataSet();
            updateListModel(collection.getStringArray());
            sizeLabel.setText("Size: " + collection.getSize());
        });
        addElement(panel, clearButton );
        addElement(panel, importButton );
        addElement(panel, generateSortButtons(collection));
        addElement(panel, generateSearchPanel(collection));
        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));

        JLabel sortSpeedTypeLabel = new JLabel(collection instanceof TreeMap<?,?> ? "Balancing:" : "Bubble Sort:");
        sortSpeedTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        speedPanel.add(sortSpeedTypeLabel);
        addElement(speedPanel, sortSpeedLabel);
        speedPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel binarySearchSpeedTypeLabel = new JLabel("Binary Search:");
        binarySearchSpeedTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        speedPanel.add(binarySearchSpeedTypeLabel);
        addElement(speedPanel, binarySearchSpeedLabel);
        speedPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel sequentialSearchSpeedTypeLabel = new JLabel("Sequential Search:");
        sequentialSearchSpeedTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        speedPanel.add(sequentialSearchSpeedTypeLabel);
        addElement(speedPanel, sequentialSearchSpeedLabel);
        addElement(panel, speedPanel);
        add(panel, gbc);


        JList<String> resultList = new JList<>(resultModel);
        resultList.setBorder(new LineBorder(Color.BLACK));

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(resultList, BorderLayout.CENTER);
        resultPanel.add(new JLabel("Search results:"), BorderLayout.NORTH);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;

        add(resultPanel, gbc);


        updateListModel(collection.getStringArray());
        JList<String> list = new JList<>(listModel);
        list.setBorder(new LineBorder(Color.BLACK));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(new JLabel("Collection contents:"), BorderLayout.NORTH);

        gbc.gridx = 2; // set the row
        gbc.gridwidth = 1;
        gbc.weightx = 5.0;

        add(listPanel, gbc);
    }

    private void addElement(JPanel panel, JComponent component, int height){
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(0, height)));
    }

    private void addElement(JPanel panel, JComponent component){
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel childPanel = new JPanel();
        childPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        childPanel.add(component);
        panel.add(childPanel);
    }

    private void loadDataSet(){
        List<Student> students = new DataLoader().loadStudents();
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

            //We did not have time to make a nice add method that worked on all the collections, due to one of them needing keys and the rest not.
            if(collection instanceof TreeMap<?, ?>){
                ((TreeMap<Integer, Student>) collection).add(((TreeMap<Integer, Student>) collection).root, key, student);
            } else if(collection instanceof CircularBuffer<?>){
                ((CircularBuffer<Student>) collection).add(student);
            } else if(collection instanceof LinkedList<?, ?>){
                ((LinkedList<Integer, Student>) collection).add(key, student);
            }
        }
        if(collection instanceof TreeMap<?, ?>) {
            ((TreeMap<Integer, Student>) collection).populateNodeWithNodes(((TreeMap<Integer, Student>) collection).root);
        }
        sizeLabel.setText("Size: " + collection.getSize());

    }

    private JPanel generateSearchPanel(CustomCollection<Student> collection){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JPanel test = new JPanel();
        test.add(new JLabel("Searching:"));
        panel.add(test);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel resultLabel = new JLabel("Result:");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JTextField jTextField = new JTextField();

        jTextField.setSize(200, 20);
        jTextField.setMaximumSize(new Dimension(200, 20));
        inputPanel.add(jTextField);

        addElement(panel, inputPanel, 15);
        panel.add(inputPanel);

        JButton sequentialSearch = new JButton("Sequential Search");
        sequentialSearch.setAlignmentX(CENTER_ALIGNMENT);
        sequentialSearch.addActionListener(e -> {
            SequentialSearch<String, Student> search = new SequentialSearch<>();

            long start = System.nanoTime();
            Set<Student> students = search.search(jTextField.getText(), collection, Student::getFirstName);
            students.addAll(search.search(jTextField.getText(), collection, Student::getLastName));
            long end = System.nanoTime();
            sequentialSearchSpeedLabel.setText((end - start) / 1000_000f + " ms ");
            resultModel.clear();
            if(students.isEmpty()){
                resultModel.addElement("Not found");
            } else {
                for(Student student : students){
                    resultModel.addElement(student.toString());
                }
            }

        });
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(resultLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addElement(panel, sequentialSearch, 15);

        JButton binarySearch = new JButton("Binary Search");
        binarySearch.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(binarySearch);
        return panel;
    }

    private JPanel generateSortButtons(CustomCollection<Student> collection){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        if(collection instanceof TreeMap<?,?>){
            JButton sort = new JButton("Balance");
            sort.addActionListener(e -> {
                balanceMap((TreeMap<Integer, Student>) collection);
            });
            addElement(panel, new JLabel("Balancing:"));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            addElement(panel, sort);
        } else {
            addElement(panel, new JLabel("Bubble Sort:"));
            JButton firstNameSort = new JButton("Sort by first name");
            firstNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getFirstName));
            });
            addElement(panel, firstNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton lastNameSort = new JButton("Sort by last name");
            lastNameSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getLastName));
            });
            addElement(panel, lastNameSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton ageSort = new JButton("Sort by age");
            ageSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getAge));
            });
            addElement(panel, ageSort);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton studentNumberSort = new JButton("Sort by student number");
            studentNumberSort.addActionListener(e -> {
                bubbleSort(collection, Comparator.comparing(Student::getStudentNumber));
            });
            addElement(panel, studentNumberSort);
        }
        return panel;
    }

    private void bubbleSort(CustomCollection<Student> collection, Comparator<Student> comparator){
        BubbleSorter<Student> bubbleSorter = new BubbleSorter<>();
        long start = System.nanoTime();
        Comparable<Student>[] arr = bubbleSorter.sort(collection, comparator);
        collection.setArray(arr);
        long end = System.nanoTime();
        sortSpeedLabel.setText((end - start) / 1000_000f + " ms ");
        updateListModel(collection.getStringArray());
    }

    private void balanceMap(TreeMap<Integer, Student> treeMap){
        long start = System.nanoTime();
        treeMap.balanceTree(treeMap.root);
        long end = System.nanoTime();
        sortSpeedLabel.setText((end - start) / 1000_000f + " ms ");
        treeMap.populateNodeWithNodes(treeMap.root);
        updateListModel(treeMap.getStringArray());
    }

    private void updateListModel(String[] array){
        listModel.clear();
        for(String s : array){
            listModel.addElement(s);
        }
    }
}
