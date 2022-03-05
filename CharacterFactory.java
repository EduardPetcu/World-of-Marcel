package com.company;

public class CharacterFactory {

    public static Character createCharacter(String CharacterType, String name, int exp, int level){
        if(CharacterType == null)
            return null;
        if(CharacterType.equalsIgnoreCase("WARRIOR"))
            return new Warrior(name,level,exp);
        else if(CharacterType.equalsIgnoreCase("MAGE"))
            return new Mage(name,level,exp);
        else if(CharacterType.equalsIgnoreCase("ROGUE"))
            return new Rogue(name,level,exp);
        else {
            System.out.println("Tipul caracterului introdus este invalid!");
            return null;
        }
    }
}
