package com.company;

import java.util.ArrayList;

public abstract class Entity implements EntityElement <Entity> {
    public ArrayList <Spell> spells;
    public int maxHealth,maxMana,critChance;
    private int currentHealth,currentMana;
    public ArrayList <Boolean> protection = new ArrayList<>(3);

    public void manaRegen(int regen){
        this.currentMana = Math.min(this.maxMana,this.currentMana + regen);
    }
    public void healthRegen(int regen) {
        this.currentHealth = Math.min(this.maxHealth,this.currentHealth + regen);
    }
    public abstract void receiveDamage(int damage);
    public abstract int getDamage();
    abstract public String className();
    public void spellUsage(Spell spell, Entity target) {
            System.out.println(target.getClass().getSimpleName() + " a fost lovit de un spell primind " + spell.damage);
            target.receiveDamage(spell.damage);
    }
    public boolean enoughMana(Spell spell){
        if(this.currentMana < spell.manaCost) {
            System.out.println(this.className() + " nu are destula mana pentru a folosi un spell!");
            return false;
        }
        return true;
    }
    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
    }
    public void setCurrentMana(int currentMana){
        this.currentMana = currentMana;
    }
    public int getCurrentHealth(){
        return this.currentHealth;
    }
    public int getCurrentMana(){
        return this.currentMana;
    }
}
