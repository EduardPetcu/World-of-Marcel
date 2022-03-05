package com.company;

public class HealthPotion implements Potion {
    private final int price, weight, regen;
    public HealthPotion(){
        this.price = 45;
        this.weight = 1;
        this.regen = 150;
    }
    public int getPrice() {
        return this.price;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getRegen() {
        return this.regen;
    }
}
