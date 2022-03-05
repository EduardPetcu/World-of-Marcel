package com.company;

import java.util.Scanner;

abstract public class Character extends Entity {
    public String name;
    public int x,y;
    private int exp,level;
    public int strength, charisma, dexterity;
    public Inventory inventory;
    boolean buyItem(Potion potion){
        return this.inventory.getGold() >= potion.getPrice() && this.inventory.weightLeft() >= potion.getWeight();
    }
    void startingStats(){
            this.setCurrentHealth(this.maxHealth);
            this.setCurrentMana(this.maxMana);
            this.y=0;
            this.x=0;
        }
    void potionUsage (int index){
            try {
                if ((index >= this.inventory.potions.size() && index != 'Q' - '1' && index != 'q' - '1') || index < 0)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Nu exista o potiune la acel indice! Selectati 'Q' pentru a inchide meniul de potiuni!");
                Scanner scanner = new Scanner(System.in);
                int newIndex = scanner.next().charAt(0);
                potionUsage(newIndex - '1');
                return;
            }
            if(index == 'Q' - '1' || index == 'q' - '1')
                return;
            if(this.inventory.potions.get(index).getClass().getSimpleName().equalsIgnoreCase("HealthPotion"))
            {
                healthRegen(inventory.potions.get(index).getRegen());
                inventory.removePotion(inventory.potions.get(index));
            }
            else if(this.inventory.potions.get(index).getClass().getSimpleName().equalsIgnoreCase("ManaPotion"))
            {
                manaRegen(inventory.potions.get(index).getRegen());
                inventory.potions.remove(index);
            }
    }
    public String toString(){
        return name +
                " ".repeat(Math.max(0, 25 - name.length())) +
                className() +
                " ".repeat(Math.max(0, 12 - className().length())) +
                "Level: " + level + " Experience:" + exp;
    }
    public String yourStats(){
        String stats="Stats:\n";
        stats+="Health:  " + this.getCurrentHealth() + "\\" + maxHealth + "\n";
        stats+="Mana:   " + this.getCurrentMana() + "\\" + maxMana + "\n";
        stats+="Level " + level + "\n";
        stats+="Experience  " + exp + "\\" + "100\n";
        stats+="Strength   " + strength + "\n";
        stats+="Dexterity   " + dexterity + "\n";
        stats+="Charisma    " + charisma + "\n";
        return stats;
    }
    public String yourSpells(){
        StringBuilder yourSpells= new StringBuilder();
        int i;
        for(i=0;i< spells.size();i++){
            yourSpells.append(i + 1).append(". ").append(spells.get(i).getClass().getSimpleName()).append(" Damage: ").append(spells.get(i).damage).append(" Mana cost: ").append(spells.get(i).manaCost).append("\n");
        }
        return yourSpells.toString();
    }
    public String showPotions(){
        StringBuilder yourPotions= new StringBuilder();
        int i;
        for(i=0;i<inventory.potions.size();i++){
            yourPotions.append(i + 1).append(". ").append(inventory.potions.get(i).getClass().getSimpleName()).append("\n");
        }
        return yourPotions.toString();
    }
    public void levelup(){
        exp = exp % 100;
        level ++;
        this.setCurrentHealth(this.getCurrentHealth() + 40);
        this.maxHealth += 40;
        this.setCurrentMana(this.getCurrentMana()+30);
        this.maxMana += 30;
        this.strength ++;
        this.dexterity ++;
        this.charisma ++;
        if(this.strength % 5 == 0){
           System.out.println("Acum poti cara inca o potiune in inventar!");
           this.inventory.setMaxWeight(this.inventory.getMaxWeight()+1);
        }
        this.critChance +=5;
    }
    public int getExp(){
        return this.exp;
    }
    public void setExp(int exp){
        this.exp = exp;
    }
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}
