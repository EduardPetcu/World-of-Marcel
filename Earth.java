package com.company;

public class Earth extends Spell {
    public Earth(){
        this.damage=35;
        this.manaCost=20;
    }
    public Earth(int damage, int manaCost){
        this.damage=damage;
        this.manaCost=manaCost;
    }
    public void visit(Entity entity) {
        if(entity.protection.get(1)){
            entity.spellUsage(new Earth(9,20),entity);
        }
        else
            entity.spellUsage(new Earth(),entity);
    }
}