package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character{
    public Rogue(String name, int level, int experience){
        this(level);
        this.name=name;
        this.setLevel(level);
        this.setExp(experience);
    }
    public Rogue(int level){
        this.strength=6 + level;
        this.inventory = new Inventory(this.strength);
        this.dexterity=10 + level;
        this.charisma=3 + level;
        this.maxHealth=500 + 40 * level;
        this.maxMana=300 + 20 * level;
        this.critChance = 10 + 5 * level;
        this.protection.add(false); // fire immunity
        this.protection.add(true); // earth immunity
        this.protection.add(false); // ice immunity
        startingStats();
        this.spells = new ArrayList<>();
        Spell spell = new Earth();
        spells.add(0,spell);
        spells.add(1,spell);
        spell = new Ice();
        spells.add(2,spell);
    }
    public int getDamage() {
        Random rand1 = new Random();
        int damage, nrrand = rand1.nextInt(100);
        damage = strength + 2 * dexterity + charisma;
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
        int procent = 4 * dexterity + charisma + strength;
        procent = Math.min(80,procent);
        Random rand1 = new Random();
        int nrrand  = rand1.nextInt(100);
        if(nrrand < procent)
            {
                damage = damage/2;
                System.out.println("Ai redus damage-ul inamicului la: " + damage);
            }
        this.setCurrentHealth(Math.max(0,this.getCurrentHealth()-damage));
    }

    @Override
    public void accept(SpellVisitor visitor) {
        visitor.visit(this);
    }
}
