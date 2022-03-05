package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FereastraCont extends JFrame implements ActionListener {
        JButton button;
        JTextField emailField,passwordField,numeUserField,countryField,numeCaracterField,nrMapsField;
        JLabel mailLabel,passLabel,numeUserLabel,countryLabel,nrMapsLabel,gamesPlayed,numeProfesie,numeCaracterLabel;
        JComboBox<String> characterChoice;
        List<Checkbox> ListaJocuri = new ArrayList<>();
        String[] jocuri = {"Metin","4Story","World of Warcraft","Need for Speed","GTA V"};
        String[] professions ={"WARRIOR","MAGE","ROGUE"};

    public FereastraCont (){
            this.setTitle("Create Account");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            emailField = new JTextField();
            emailField.setPreferredSize(new Dimension(150,20));
            passwordField = new JTextField();
            passwordField.setPreferredSize(new Dimension(125,20));
            numeUserField = new JTextField();
            numeUserField.setPreferredSize(new Dimension(125,20));
            countryField = new JTextField();
            countryField.setPreferredSize(new Dimension(125,20));
            nrMapsField = new JTextField();
            nrMapsField.setPreferredSize(new Dimension(80,20));
            mailLabel = new JLabel("Email");
            passLabel = new JLabel("Password");
            numeUserLabel = new JLabel("Nume utilizator");
            countryLabel = new JLabel("Tara");
            nrMapsLabel = new JLabel("Numar de mape jucate:");
            gamesPlayed = new JLabel("Alege jocurile jucate:");
            numeProfesie = new JLabel("Alegeti profesia caracterului!");
            characterChoice = new JComboBox<>(professions);
            numeCaracterLabel = new JLabel("Introduceti numele caracterului!");
            numeCaracterField = new JTextField();
            numeCaracterField.setPreferredSize(new Dimension(125,20));
            this.add(mailLabel);
            this.add(emailField);
            this.add(passLabel);
            this.add(passwordField);
            this.add(numeUserLabel);
            this.add(numeUserField);
            this.add(countryLabel);
            this.add(countryField);
            this.add(nrMapsLabel);
            this.add(nrMapsField);
            this.add(gamesPlayed);
        for (String s : jocuri) {
            Checkbox checkbox = new Checkbox(s);
            ListaJocuri.add(checkbox);
            this.add(checkbox);
        }
            this.add(numeCaracterLabel);
            this.add(numeCaracterField);

             this.add(numeProfesie);
             this.add(characterChoice);

            button = new JButton("Creeaza cont!");
            button.addActionListener(this);
            this.add(button);
            ImageIcon image = new ImageIcon("wow-icon.jpg");
            this.setIconImage(image.getImage());
            this.getContentPane().setBackground(Color.gray);
            this.setLayout(new FlowLayout());
            this.pack();
            this.setSize(new Dimension(800,450));
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            try {
                accountCreation();
            } catch (InformationIncompleteException ignored) {
            }
        }
    }
    public void accountCreation() throws InformationIncompleteException{
        Credentials credits = new Credentials();
        credits.setEmail(emailField.getText());
        credits.setPassword(passwordField.getText());
        ArrayList<String> favoriteGames = new ArrayList<>();
        ArrayList<Character> listCharacters = new ArrayList<>();
        for (int i = 0; i < ListaJocuri.size(); i++) {
            if (ListaJocuri.get(i).getState())
                favoriteGames.add(jocuri[i]);
        }
        if(numeUserField.getText().equals("") || countryField.getText().equals("") ||
              favoriteGames.size()==0 || credits.getPassword().equals("") || credits.getEmail().equals("") ||
                numeCaracterField.getText().equals(""))
                throw new InformationIncompleteException();
        Character hero = CharacterFactory.createCharacter((String) characterChoice.getSelectedItem(), numeCaracterField.getText(), 0, 1);
        listCharacters.add(hero);
        Account.Information userInfo = new Account.Information.InformationBuilder(credits)
                .setName(numeUserField.getText())
                .setCountry(countryField.getText())
                .setFavouriteGames(favoriteGames)
                .build();
        Account user;
        try {
            user = new Account(userInfo, listCharacters, parseInt(nrMapsField.getText()));
        } catch (NumberFormatException e) {
            return;
        }
        Game.accounts.add(user);
        Grid grid = Grid.getInstance();
        Game.gridInit(grid, hero);
        this.setVisible(false);
        Game.startGame(true);
        dispose();
    }
}
