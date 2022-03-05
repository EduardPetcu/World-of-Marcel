package com.company;

import java.util.Scanner;

public interface Potion {
    int getPrice();
    int getWeight();
    int getRegen();
    static void usePotion(Character hero) {
        System.out.println("Alege din lista de mai jos potiunea pe care vrei s-o utiliezi folosind cifre de la 1 la 9");
        System.out.println("Apasati 'Q' pentru a inchide meniul de potiuni!");
        Scanner scanMove = new Scanner(System.in);
        char command;
        while (true) {
            System.out.println(hero.showPotions());
            command = scanMove.next().charAt(0);
            if (command <= '9' && command >= '0') {
                {
                    hero.potionUsage(command - '1');
                    System.out.println("Alege din lista de mai jos potiunea pe care vrei s-o utiliezi folosind cifre de la 1 la 9");
                    System.out.println("Apasati 'Q' pentru a inchide meniul de potiuni!");
                }
            } else if (command == 'Q' || command == 'q')
                break;
            else if (command == 'C' || command == 'c')
                System.out.println(hero.yourStats());
            else
                System.out.println("Aceea nu este o potiune! Selectati o potiune din lista de mai jos!");
        }
    }
}
