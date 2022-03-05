package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Warrior extends Character{
    public Warrior(String name, int level, int experience){
        this(level);
        this.name=name;
        this.setLevel(level);
        this.setExp(experience);
    }
    public Warrior(int level){
        this.strength=10 + level;
        this.inventory = new Inventory(this.strength);
        this.dexterity=3 + level;
        this.charisma=6 + level;
        this.critChance = 15 + 5 * level;
        this.maxHealth=550 + 40 * level;
        this.maxMana=220 + 20 * level;
        this.protection.add(true); // fire immunity
        this.protection.add(false); // earth immunity
        this.protection.add(false); // ice immunity
        this.spells = new ArrayList<>();
        Spell spell = new Fire();
        spells.add(0,spell);
        spells.add(1,spell);
        spell = new Earth();
        spells.add(2,spell);
        startingStats();
    }

    public int getDamage() {
        Random rand1 = new Random();
        int damage,nrrand  = rand1.nextInt(100);
        damage = 4 * strength + dexterity + charisma;
        if(nrrand < critChance) {
            damage *= 2;
            System.out.println("Critical Strike! Eroul tau produce damage dublu: " + damage);
        }
        else
            System.out.println("Eroul tau produce damage normal: " + damage);
        return damage;
    }

    public String className() {
        return getClass().getSimpleName();
    }

    public void receiveDamage(int damage) {
        int procent = dexterity + charisma + 2 * strength;
        procent = Math.min(80,procent);
        Random rand1 = new Random();
        int nrrand  = rand1.nextInt(100);
        if(nrrand < procent) {
                damage = damage/2;
                System.out.println("Ai redus damage-ul inamicului la: " + damage);
            }
        this.setCurrentHealth(Math.max(0,this.getCurrentHealth()-damage));
    }

    public void accept(SpellVisitor visitor) {
        visitor.visit(this);
    }
}
