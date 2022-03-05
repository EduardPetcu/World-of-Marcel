package com.company;

public class ManaPotion implements Potion {
    private final int price, weight, regen;
    public ManaPotion(){
        this.price = 35;
        this.weight = 1;
        this.regen = 100;
    }
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public int getRegen() {
        return this.regen;
    }
}
