package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Shop implements CellElement{

    public ArrayList <Potion> potionShop;
    public Shop(){
        this.potionShop = new ArrayList<>(4);
        Random rand1 = new Random();
        this.potionShop.add(0,new HealthPotion());
        this.potionShop.add(1,new ManaPotion());
        for(int i=2;i<4;i++) {
            int nrrand = rand1.nextInt(2);
            if (nrrand == 0) {
                this.potionShop.add(i,new HealthPotion());
            }
            else
                this.potionShop.add(i,new ManaPotion());
        }
    }
    public Potion sellPotion(int index){
        Potion potionToSell;
        potionToSell = potionShop.get(index);
        potionShop.remove(index);
        return potionToSell;
    }
    public String toString(){
        StringBuilder showShop= new StringBuilder("====================\n");
        int i;
        for(i=0;i<this.potionShop.size();i++){
            showShop.append(i + 1).append(" | ").append(this.potionShop.get(i).getClass().getSimpleName()).append(" ");
            if(this.potionShop.get(i).getClass().getSimpleName().equalsIgnoreCase("ManaPotion")){
                showShop.append("  ");
            }
            showShop.append(this.potionShop.get(i).getPrice()).append("|\n");
        }
        showShop.append("====================");
        return showShop.toString();
    }

    public void toCharacter() {
        System.out.print("SHOP");
    }
}
