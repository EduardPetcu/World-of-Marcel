package com.company;

public class Fire extends Spell {
    public Fire(){
        this.damage=55;
        this.manaCost=45;
    }
    public Fire (int damage, int manaCost){
        this.damage=damage;
        this.manaCost=manaCost;
    }
    public void visit(Entity entity) {
        if(entity.protection.get(0)){
            entity.spellUsage(new Fire(15,45),entity);
        }
        else
            entity.spellUsage(new Fire(),entity);
    }
}