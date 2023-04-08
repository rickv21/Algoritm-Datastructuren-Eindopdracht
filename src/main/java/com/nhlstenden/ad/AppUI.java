package com.nhlstenden.ad;

import com.nhlstenden.ad.data.CustomCollection;

import javax.swing.*;

public class AppUI extends JFrame {

    private final JTabbedPane tabbedPane = new JTabbedPane();

    public AppUI(){
        setSize(900, 900);
        setTitle("Algoritme & Datastructuren");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(tabbedPane);
    }

    public void addTab(String title, CustomCollection<Student> collection){
        tabbedPane.addTab(title, null, new UITab(collection, title));
    }
}
