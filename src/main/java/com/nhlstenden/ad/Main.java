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

        JPanel bufferPanel = new JPanel(new GridLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

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

        bufferPanel.add(panel);

        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Item1");
        l1.addElement("Item2");
        l1.addElement("Item3");
        l1.addElement("Item4");
        JList<String> list = new JList<>(l1);
        list.setBorder(new LineBorder(Color.BLACK));


        JPanel listPanel = new JPanel();
        //listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(new JLabel("Collection contents:"), BorderLayout.NORTH);
        //listPanel.add(list);


        bufferPanel.add(listPanel);


        tabbedPane.addTab("Circular Buffer", null, bufferPanel,
                "Buffer");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel linkedListPanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("Linked List", null, linkedListPanel,
                "List");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        frame.add(tabbedPane);
        frame.setVisible(true);


    }
}