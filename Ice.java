package com.company;

public class Ice extends Spell {
    // standard values
    public Ice(){
        damage=90;
        manaCost=60;
    }
    // specific values
    public Ice(int damage, int manaCost){
        this.damage=damage;
        this.manaCost=manaCost;
    }
    public void visit(Entity entity) {
        if(entity.protection.get(2)){
            entity.spellUsage(new Ice(20,60),entity);
        }
        else
            entity.spellUsage(new Ice(),entity);
    }
}
