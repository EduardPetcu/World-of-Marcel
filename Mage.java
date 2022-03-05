package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Mage extends Character{
    public Mage(String name, int level, int experience){
        this(level);
        this.name=name;
        this.setLevel(level);
        this.setExp(experience);
    }
    public Mage(int level){
        this.strength=3 + level;
        this.inventory = new Inventory(this.strength);
        this.dexterity=6 + level;
        this.charisma=10 + level;
        this.maxHealth=460 + 40 * level;
        this.maxMana=400 + 20 * level;
        this.critChance = 5 + 5 * level;
        this.protection.add(false); // fire immunity
        this.protection.add(false); // earth immunity
        this.protection.add(true); // ice immunity
        startingStats();
        this.spells = new ArrayList<>();
        Spell spell = new Ice();
        spells.add(0,spell);
        spells.add(1,spell);
        spell = new Fire();
        spells.add(2,spell);
    }
    public int getDamage() {
        Random rand1 = new Random();
        int damage,nrrand  = rand1.nextInt(100);
        damage = strength + dexterity + 4 * charisma;
        if(nrrand < critChance)
            {
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
        int procent = dexterity + 2 * charisma + strength;
        procent = Math.min(80,procent);
        Random rand1 = new Random();
        int nrrand  = rand1.nextInt(100);
        if(nrrand < procent) {
            damage = damage / 2;
            System.out.println("Ai redus damage-ul inamicului la: " + damage);
        }
        this.setCurrentHealth(Math.max(0,this.getCurrentHealth()-damage));
    }
    public void accept(SpellVisitor visitor) {
        visitor.visit(this);
    }

}
