package com.company;


import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity implements CellElement{
    ArrayList <Spell> spells;
    public int damage;
    public Enemy(int level){
        this.maxHealth = 300 + 30 * level;
        this.maxMana = 220 + 15 * level;
        this.setCurrentHealth(this.maxHealth);
        this.setCurrentMana(this.maxMana);
        this.critChance = 25;
        this.damage = 15;
        this.spells = new ArrayList<>();
        Random rand1 = new Random();
        int nrrand = rand1.nextInt(3);
        if(nrrand == 0){
            this.protection.add(true);
            this.protection.add(false);
            this.protection.add(false);
        }
        if(nrrand == 1){
            this.protection.add(false);
            this.protection.add(true);
            this.protection.add(false);
        }
        else
        {
            this.protection.add(false);
            this.protection.add(false);
            this.protection.add(true);
        }
        for(int i=0;i<3;i++) {
            nrrand = rand1.nextInt(3);
            Spell spell;
            if (nrrand == 0) {
                spell = new Fire();
                spells.add(i, spell);
            }
            if (nrrand == 1) {
                spell = new Earth();
                spells.add(i, spell);
            }
            if (nrrand == 2) {
                spell = new Ice();
                spells.add(i, spell);
            }
        }
    }
    public void healthRegen(int regen) {
        this.setCurrentHealth(Math.max(this.maxHealth,this.getCurrentHealth()+regen));
    }

    public String className() {
        return getClass().getSimpleName();
    }

    public void manaRegen(int regen) {
        this.setCurrentMana(Math.max(this.maxMana,this.getCurrentMana()+regen));
    }

    public void receiveDamage(int damage) {
        Random rand1 = new Random();
        int nrrand  = rand1.nextInt(2);
        if(nrrand == 0)
            {
                damage = damage/2; // reduce damage
                System.out.println("Inamicul primeste damage redus: " + damage);
            }
        this.setCurrentHealth(Math.max(0,this.getCurrentHealth()-damage));
        if(this.getCurrentHealth() == 0)
            System.out.println("Ai ucis un inamic!");
    }
    public int getDamage() {
        Random rand1 = new Random();
        int damage,nrrand  = rand1.nextInt(15);
        damage = this.damage + nrrand;
        nrrand = rand1.nextInt(100);
        if(nrrand < critChance)
            {
                damage *= 2;
                System.out.println("Critical strike! Inamicul ti-a produs " + damage + " damage!");
            }
        else
            System.out.println("Inamicul a folosit o lovitura normala: " + damage + " damage!");
        return damage;
    }

    public void toCharacter() {
        System.out.print("ENEMY");
    }
    public void accept(SpellVisitor visitor) {
        visitor.visit(this);
    }
    public String enemyStats(){
        String stats="Enemy stats:\n";
        stats+="Health:  " + this.getCurrentHealth() + "\\" + this.maxHealth + "\n";
        stats+="Mana:   " + this.getCurrentMana() + "\\" + this.maxMana + "\n";
        stats+="Damage   " + this.damage + "\n";
        stats+="Proection: ";
        if(this.protection.get(0))
            stats+="Fire\n";
        else if(this.protection.get(1))
            stats+="Earth\n";
        else
            stats+="Ice\n";
        return stats;
    }
}
