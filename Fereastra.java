package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fereastra extends JFrame implements ActionListener {
    JButton button,characterButton,createNewCharacter,finishCreatingCharacter,createNewAccount;
    JTextField text,password,numeCaracterField;
    JLabel mail,pass,eroare,numeCaracterLabel,numeProfesie;
    JComboBox<Character> charactersComboBox;
    JComboBox<String> characterChoice;
    int contor = 3,indexContSelectat;
    public Fereastra (String numeFereastra){
        this.setTitle(numeFereastra);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text = new JTextField();
        text.setPreferredSize(new Dimension(125,20));
        password = new JTextField();
        password.setPreferredSize(new Dimension(125,20));
        mail = new JLabel("Email");
        pass = new JLabel("Password");
        eroare = new JLabel("Ai introdus de prea multe ori credentiale gresite!");
        this.add(mail);
        this.add(text);
        this.add(pass);
        this.add(password);
        button = new JButton("Submit credentials!");
        button.addActionListener(this);
        createNewAccount = new JButton("Creaza cont nou!");
        createNewAccount.addActionListener(this);
        this.add(button);
        this.add(createNewAccount);
        ImageIcon image = new ImageIcon("wow-icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(new FlowLayout());
        this.pack();
        this.setSize(new Dimension(800,450));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            Credentials credentials = new Credentials();
            credentials.setEmail(text.getText());
            credentials.setPassword(password.getText());
            for (int i = 0; i < Game.accounts.size(); i++)
                if (Game.accounts.get(i).getInfo().getCredentials().getEmail().equals(credentials.getEmail())
                        && Game.accounts.get(i).getInfo().getCredentials().getPassword().equals(credentials.getPassword())) {
                    charactersComboBox = new JComboBox<>();
                    indexContSelectat = i;
                    for (int j = 0; j < Game.accounts.get(i).getCharacters().size(); j++) {
                       charactersComboBox.addItem(Game.accounts.get(i).getCharacters().get(j));
                    }
                    this.add(charactersComboBox);
                    characterButton = new JButton("Selecteaza caracter!");
                    createNewCharacter = new JButton("Creaza-ti un nou caracter!");
                    characterButton.addActionListener(this);
                    createNewCharacter.addActionListener(this);
                    this.add(characterButton);
                    this.add(createNewCharacter);
                    this.setVisible(true);
        }
            contor--;
            if(contor<=0) {
                button.setEnabled(false);
                this.add(eroare);
                this.setVisible(true);
            }
        }
        if(e.getSource()==characterButton){
            Grid grid = Grid.getInstance();
            Game.gridInit(grid, (Character) charactersComboBox.getSelectedItem());
            characterButton.setEnabled(false);
            charactersComboBox.setEnabled(false);
            createNewCharacter.setEnabled(false);
            this.setVisible(false);
            Game.startGame(true);
            dispose();
        }
        if(e.getSource()==createNewCharacter){
            createNewCharacter.setEnabled(false);
            createNewCharacter.setVisible(false);
            numeCaracterLabel = new JLabel("Introduceti numele caracterului!");
            this.add(numeCaracterLabel);
            numeCaracterField = new JTextField();
            numeCaracterField.setPreferredSize(new Dimension(125,20));
            this.add(numeCaracterField);
            numeProfesie = new JLabel("Alegeti profesia caracterului!");
            this.add(numeProfesie);
            String[] professions ={"WARRIOR","MAGE","ROGUE"};
            characterChoice = new JComboBox<>(professions);
            this.add(characterChoice);
            finishCreatingCharacter = new JButton("Finalizati crearea caracterului!");
            finishCreatingCharacter.addActionListener(this);
            this.add(finishCreatingCharacter);
            this.setVisible(true);
        }
        if(e.getSource()==finishCreatingCharacter){
            Character hero = CharacterFactory.createCharacter((String) characterChoice.getSelectedItem(),numeCaracterField.getText(),0,1);
            Grid grid = Grid.getInstance();
            Game.gridInit(grid,hero);
            Game.accounts.get(indexContSelectat).getCharacters().add(hero);
            this.setVisible(false);
            Game.startGame(true);
            dispose();
        }
        if(e.getSource()==createNewAccount){
            this.setVisible(false);
            dispose();
            FereastraCont FC = new FereastraCont();
            FC.setVisible(true);
        }
    }

}
