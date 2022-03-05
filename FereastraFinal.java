package com.company;

import javax.swing.*;
import java.awt.*;

public class FereastraFinal extends JFrame {
    JLabel numePlayer,profesie,level,experience;
    JTextArea stats;
    public FereastraFinal(Character hero){
        this.setTitle("Final joc");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("wow-icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(new FlowLayout());
        stats = new JTextArea("Nume caracter: " + hero.name + "\n",20,20);
        stats.setBackground(Color.gray);
        this.numePlayer = new JLabel("Nume caracter: " + hero.name ,SwingConstants.CENTER);
        this.profesie = new JLabel("Profesie: " + hero.getClass().getSimpleName() ,SwingConstants.CENTER);
        this.level = new JLabel("Level: " + hero.getLevel()  ,SwingConstants.CENTER);
        this.experience = new JLabel("Experienta: " + hero.getExp()  ,SwingConstants.CENTER);
        stats.append(this.profesie.getText() + "\n");
        stats.append(this.level.getText() + "\n");
        stats.append(this.experience.getText() + "\n");
        stats.setFont(new Font("Serif",Font.BOLD,30));
        this.add(stats);
        this.pack();
        this.setSize(new Dimension(650,300));
    }
}
