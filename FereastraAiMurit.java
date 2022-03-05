package com.company;

import javax.swing.*;
import java.awt.*;

public class FereastraAiMurit extends JFrame{
    JLabel numePlayer,profesie,level,experience,mesaj;
    JTextArea stats;
    public FereastraAiMurit(Character hero){
        this.setTitle("Ai murit");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("wow-icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(156, 140, 64));
        this.setLayout(new FlowLayout());
        this.mesaj = new JLabel("Ai murit!");
        stats = new JTextArea(this.mesaj.getText() + "\n",20,20);
        //stats.setBackground(new Color(102,26,26));
        this.numePlayer = new JLabel("Nume caracter: " + hero.name ,SwingConstants.CENTER);
        this.profesie = new JLabel("Profesie: " + hero.getClass().getSimpleName() ,SwingConstants.CENTER);
        this.level = new JLabel("Level: " + hero.getLevel()  ,SwingConstants.CENTER);
        this.experience = new JLabel("Experienta: " + hero.getExp()  ,SwingConstants.CENTER);
        stats.setBackground(new Color(156, 140, 64));
        stats.append(this.numePlayer.getText() + "\n");
        stats.append(this.profesie.getText() + "\n");
        stats.append(this.level.getText() + "\n");
        stats.append(this.experience.getText() + "\n");
        stats.setFont(new Font("Serif",Font.BOLD,30));
        this.add(stats);
        this.pack();
        this.setSize(new Dimension(650,300));
    }
}
