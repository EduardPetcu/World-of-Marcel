package com.company;

import java.util.ArrayList;

public class Inventory {
    public ArrayList <Potion> potions;
    private int maxWeight,gold;
    public int getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public Inventory(int strength){
        this.potions = new ArrayList<>();
        this.maxWeight = 3 + (strength / 5);
        this.gold = 90;
    }
    public void addPotion(Potion potion){
        this.potions.add(potion);
    }
    public void removePotion(Potion potion){
        this.potions.remove(potion);
    }
    public int weightLeft(){
        int i,weight=0;
        for(i=0;i<potions.size();i++){
            weight+=potions.get(i).getWeight();
        }
        return maxWeight-weight;
    }
}
