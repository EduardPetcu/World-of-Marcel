package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game extends JFrame {

    private static Game app = null;
    public static ArrayList <Account> accounts = new ArrayList<>();
    public static HashMap < CellEnum, ArrayList <String> > story = new HashMap<>();

    private Game() {

    }
    // Metoda specifica design-ului Singleton intarziat
    public static Game getInstance() {
        if (app == null)
            app = new Game();
        return app;
    }
    public static void showStory(Cell cell) {
        if(!cell.isVisited()) {
            Random rand = new Random();
            int nrrand = rand.nextInt(story.get(cell.getCT()).size());
            System.out.println((story.get(cell.getCT())).get(nrrand));
        }
    }
    public static void showMap(){
        Grid grid = Grid.getInstance();
        int i, j;
        for (i = 0; i < grid.getLength(); i++) {
            for (j = 0; j < grid.getWidth(); j++) {
                if(grid.getCelulaCurenta().x == i && grid.getCelulaCurenta().y == j) {
                    System.out.print("  P  ");
                    grid.getMatrice().get(i).get(j).setVisited();
                }
                else if(grid.getMatrice().get(i).get(j).isVisited() || (Math.abs(i-grid.getCelulaCurenta().x)<=1)&&(Math.abs(j-grid.getCelulaCurenta().y)<=1))
                    System.out.print(grid.getMatrice().get(i).get(j).getCT());
                else
                    System.out.print("  ?  ");
                System.out.print(" ");

            }
            System.out.println();
        }
    }
    public void run() throws InvalidPasswordException, InvalidCommandException {
        System.out.println("Apasati 't' daca doriti sa jucati din terminal sau 'g' daca doriti utilizarea interfetei grafice!");
        Scanner scanMove = new Scanner(System.in);
        char command = scanMove.next().charAt(0);
        Grid grid;
        Character hero;
        if(command == 't'){
            System.out.println("Jocul va rula in terminal!");
            hero = characterLogin();
            grid = Grid.getInstance();
            gridInit(grid,hero);
            Game.startGame(false);
        }
        else if(command == 'g') {
            System.out.println("Jocul va rula in interfata grafica!");
            Fereastra credentialeJFrame = new Fereastra("Credentials");
            credentialeJFrame.setVisible(true);
        }
        else
            try{
                throw new InvalidCommandException();
            }
            catch (InvalidCommandException e){
                run();
            }
    }
    public static void gridInit(Grid grid, Character hero){
        grid.setLength(5);
        grid.setWidth(5);
        grid.setCharacter(hero);
        ArrayList<ArrayList<Cell>> Test = Grid.GenerateGrid(5, 5);
        grid.setMatrice(Test);
        grid.getHero().x=0;
        grid.getHero().y=0;
    }
    
    public Character characterLogin() throws InvalidPasswordException{
        int count=3;
        char command;
        System.out.println("Introduceti emailul si parola");
        Scanner scanMove = new Scanner(System.in);
        String mail,password;
        boolean caracterSelectat = true;
        Character hero = null;
        Credentials credentials = new Credentials();
        while(count>0 && caracterSelectat) {
            mail = scanMove.next();
            password = scanMove.next();
            credentials.setEmail(mail);
            credentials.setPassword(password);
            for (int i = 0; i < Game.accounts.size(); i++)
                if (Game.accounts.get(i).getInfo().getCredentials().getEmail().equals(credentials.getEmail())
                        && Game.accounts.get(i).getInfo().getCredentials().getPassword().equals(credentials.getPassword())) {
                    System.out.println("Loggarea s-a finalizat cu succes!\nSelectati un caracter din lista de caractere!");
                    System.out.println("Pentru crearea unui cont nou, apasati Q, iar pentru utilizarea unui caracter din lista tastati indicele caracterului!");
                    for (int j = 0; j < Game.accounts.get(i).getCharacters().size(); j++) {
                        System.out.print(j+1 + "| ");
                        System.out.println(Game.accounts.get(i).getCharacters().get(j));
                    }
                    command = scanMove.next().charAt(0);
                    if(command=='Q' || command=='q') {
                        System.out.println("Alegeti una din cele 3 clase tastand o cifra de la 1 la 3!");
                        System.out.println("1. Mage      2. Rogue      3. Warrior");
                        command = scanMove.next().charAt(0);
                        while(true) {
                            if (command == '1')
                            {
                                System.out.println("Alegeti un nume pentru caracterul dvs!");
                                String nume = scanMove.next();
                                hero = CharacterFactory.createCharacter("Mage",nume,0,1);
                                caracterSelectat = false;
                                Game.accounts.get(i).getCharacters().add(hero);
                                break;
                            }
                            else if (command == '2')
                            {
                                System.out.println("Alegeti un nume pentru caracterul dvs!");
                                String nume = scanMove.next();
                                hero = CharacterFactory.createCharacter("Rogue",nume,0,1);
                                caracterSelectat = false;
                                Game.accounts.get(i).getCharacters().add(hero);
                                break;
                            }
                            else if (command == '3')
                            {
                                System.out.println("Alegeti un nume pentru caracterul dvs!");
                                String nume = scanMove.next();
                                hero = CharacterFactory.createCharacter("Warrior",nume,0,1);
                                caracterSelectat = false;
                                Game.accounts.get(i).getCharacters().add(hero);
                                break;
                            }
                            else {
                                System.out.println("Indice invalid! Alegeti un numar de la 1 la 3.");
                                command = scanMove.next().charAt(0);
                            }
                        }


                    }
                    else {
                        if (command <= Game.accounts.get(i).getCharacters().size()+'0' && command >= '1') {
                            hero = Game.accounts.get(i).getCharacters().get(command - '1');
                            System.out.println("Erou selectat cu succes!");
                            caracterSelectat = false;
                            break;
                        } else try {
                            throw new InvalidCommandException();
                        } catch (InvalidCommandException e) {
                            System.out.println("Indice invalid! Alegeti un numar de la 1 la " + Game.accounts.get(i).getCharacters().size());
                            command = scanMove.next().charAt(0);
                            hero = Game.accounts.get(i).getCharacters().get(command - '1');
                            System.out.println("Erou selectat cu succes!");
                            caracterSelectat = false;
                            break;
                        }
                    }
                    break;
                }
            count--;
            System.out.println("Introduceti din nou emailul si parola");

        }
        if(count==0)
            throw new InvalidPasswordException("Combinatia de email+parola gresita de prea multe ori!");
        return hero;
    }
    public static void movement(){
        Grid grid = Grid.getInstance();
        Scanner scanMove = new Scanner(System.in);
        char command = scanMove.next().charAt(0);
        if (command == 'A' || command == 'a') {
            grid.goWest();
        }
        else if (command == 'D' || command == 'd') {
            grid.goEast();
        }
        else if (command == 'S' || command == 's') {
            grid.goSouth();
        }
        else if (command == 'W' || command == 'w') {
            grid.goNorth();
        }
        else if (command == 'C' || command == 'c')
            System.out.println(grid.getHero().yourStats());
        else try {
                throw new InvalidCommandException();
            }
            catch (InvalidCommandException e) {
                System.out.println("Comanda invalida!");
                movement();
            }
    }
    public static void startGame(boolean graphics){
        Grid grid = Grid.getInstance();
        Random rand = new Random();
        Scanner scanMove = new Scanner(System.in);
        int nrrand;
        char command;
        showMap();
        Game.showStory(grid.getCelulaCurenta());
        while (true) {
            movement();
            grid.getCelulaCurenta().setCT(grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).getCT());
            Game.showStory(grid.getMatrice().get(grid.getCelulaCurenta().x).get(grid.getCelulaCurenta().y));
            if(grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).getCT()==CellEnum.SHOP) {
                Shop shop;
                if(grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE == null) {
                    shop = new Shop();
                    grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE = shop;
                }
                else
                    shop = (Shop) grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE;
                System.out.println("Pentru a cumpara o potiune folositi cifrele de la 1 la 4");
                System.out.println("Pentru a parasi magazinul, apasati Q");
                System.out.println(shop);
                command = scanMove.next().charAt(0);
                while (command != 'Q' && command != 'q' && shop.potionShop.size() > 0){
                    try {
                        if((command>shop.potionShop.size()+'0' || command<='0'))
                            throw new InvalidCommandException();
                    }
                    catch (InvalidCommandException e) {
                        System.out.println("Comanda invalida!");
                        command = scanMove.next().charAt(0);
                        continue;
                    }
                    if(grid.getHero().buyItem(shop.potionShop.get(command-49)))
                    {
                        grid.getHero().inventory.setGold(grid.getHero().inventory.getGold()-shop.potionShop.get(command-49).getPrice());
                        grid.getHero().inventory.addPotion(shop.sellPotion(command-49));
                    }
                    else
                        System.out.println("Nu ai destui bani/spatiu pentru a cumpara aceasta potiune!");
                    System.out.println(shop);
                    System.out.println("Gold: " + grid.getHero().inventory.getGold());
                    command = scanMove.next().charAt(0);
                }
            }
            // daca celula este de tip EMPTY si nu e vizitata
            if(grid.getCelulaCurenta().getCT()==CellEnum.EMPTY &&
                    !grid.getMatrice().get(grid.getCelulaCurenta().x).get(grid.getCelulaCurenta().y).isVisited()){
                nrrand= rand.nextInt(5);
                if(nrrand==0) {
                    grid.getHero().inventory.setGold(grid.getHero().inventory.getGold()+15);
                    System.out.println("Ai gasit 15 gold pe jos!");
                    System.out.println("Gold: " + grid.getHero().inventory.getGold());
                }
            }
            if(grid.getCelulaCurenta().getCT()==CellEnum.FINISH){
                showMap();
                SaveAccount.saveAccounts();
                FereastraFinal FF;
                if(graphics) {
                    FF = new FereastraFinal(grid.getHero());
                    FF.setVisible(true);
                }
                else
                    System.out.println(grid.getHero());
                break;
            }
            if(grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).getCT()==CellEnum.ENEMY){
                    Enemy enemy;
                    if(grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE == null) {
                        enemy = new Enemy(grid.getHero().getLevel());
                        grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE = enemy;
                    }
                    else
                        enemy = (Enemy) grid.getMatrice().get(grid.getHero().x).get(grid.getHero().y).CE;
                    while(enemy.getCurrentHealth()>0 && grid.getHero().getCurrentHealth()>0){
                        //Your turn:
                        System.out.println("Apasa 'A' pentru un atac normal, 'S' pentru a utiliza un spell si 'P' " +
                                "pentru a folosi o potiune din inventar!");
                        System.out.println("Apasa 'C' pentru a vedea statsurile curente si 'E' pentru a vedea statsurile inamicului! ");
                        while(true) {
                            command = scanMove.next().charAt(0);
                            if (command == 'A' || command == 'a') {
                                enemy.receiveDamage(grid.getHero().getDamage());
                                break;
                            } else if (command == 'S' || command == 's') {
                                System.out.println("Alege din lista de mai jos abilitatea pe care vrei s-o utiliezi!");
                                while (true) {
                                    System.out.println(grid.getHero().yourSpells());
                                    command = scanMove.next().charAt(0);
                                    if (command == '1' || command == '2' || command == '3') {
                                        if (grid.getHero().enoughMana(grid.getHero().spells.get(command - 49)))
                                        {
                                            enemy.accept(grid.getHero().spells.get(command - 49));
                                            grid.getHero().manaRegen(-1 * grid.getHero().spells.get(command-49).manaCost);
                                        }
                                        break;
                                    } else
                                        System.out.println("Aceea nu este o abilitate! Selectati o abilitate din lista de mai jos!");
                                }
                                break;
                            } else if (command == 'C' || command == 'c')
                                System.out.println(grid.getHero().yourStats());
                            else if (command == 'E' || command == 'e')
                                System.out.println(enemy.enemyStats());
                            else if (command == 'P' || command == 'p') {
                                Potion.usePotion(grid.getHero());
                                break;
                            }
                            else System.out.println("Comanda indisponibila!");
                        }
                        // Enemy's turn:
                        if(enemy.getCurrentHealth()<=0)
                            continue;
                        nrrand = rand.nextInt(4);
                        if(nrrand == 0) {
                            nrrand = rand.nextInt(3);
                            if (enemy.enoughMana(enemy.spells.get(nrrand))) {
                                grid.getHero().accept(enemy.spells.get(nrrand));
                                enemy.manaRegen(-1 * enemy.spells.get(nrrand).manaCost);
                            }
                            else grid.getHero().receiveDamage(enemy.getDamage());
                        }
                        else grid.getHero().receiveDamage(enemy.getDamage());
                    }
                    if(grid.getHero().getCurrentHealth()<=0) {
                        if (graphics) {
                            FereastraAiMurit FAM = new FereastraAiMurit(grid.getHero());
                            FAM.setVisible(true);
                        }
                        else
                        {
                            System.out.println("Ai murit!");
                        }
                        break;
                    }
                    if(enemy.getCurrentHealth()<=0){
                        grid.getHero().setExp(grid.getHero().getExp()+75);
                        nrrand = rand.nextInt(5);
                        if(nrrand!=0)
                            grid.getHero().inventory.setGold(grid.getHero().inventory.getGold()+80);
                    }
                    if(grid.getHero().getExp()>0)
                        grid.getHero().levelup();
                }
            showMap();
        }
    }

}
