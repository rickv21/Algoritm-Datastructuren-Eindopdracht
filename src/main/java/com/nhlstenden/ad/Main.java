package com.nhlstenden.ad;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("Algoritme & Datastructuren");

        JTabbedPane tabbedPane = new JTabbedPane();

        CustomCollection placeholderCollection = () -> 0;

        tabbedPane.addTab("Circular Buffer", null, generateTabContent(placeholderCollection, "Circular Buffer"));
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Linked List", null, generateTabContent(placeholderCollection, "Linked List"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Tree Map", null, generateTabContent(placeholderCollection, "Tree Map"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel generateTabContent(CustomCollection collection, String title){
        JPanel contentPanel = new JPanel(new GridLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
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

        JButton sort1 = new JButton("Sort1");
        panel.add(sort1);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton sort2 = new JButton("Sort2");
        panel.add(sort2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton search1 = new JButton("Search1");
        panel.add(search1);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton search2 = new JButton("Search2");
        panel.add(search2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Speed:");
        speedPanel.add(label);

        panel.add(speedPanel);

        contentPanel.add(panel);

        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Item1");
        l1.addElement("Item2");
        l1.addElement("Item3");
        l1.addElement("Item4");
        JList<String> list = new JList<>(l1);
        list.setBorder(new LineBorder(Color.BLACK));


        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(new JLabel("Collection contents:"), BorderLayout.NORTH);


        contentPanel.add(listPanel);

        return contentPanel;
    }
}