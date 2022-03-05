package com.company;

import java.util.ArrayList;
import java.util.Random;
public class Grid extends ArrayList {
    private static Grid tabla = null;
    private ArrayList <ArrayList <Cell>> matrice;
    private int length,width;
    private Character character;
    public Cell celulaCurenta = new Cell();

    public Character getCharacter() {
        return character;
    }
    public int getWidth() {
        return width;
    }
    public int getLength() {
        return length;
    }
    public ArrayList<ArrayList <Cell> > getMatrice(){
        return matrice;
    }
    public Cell getCelulaCurenta() {
        return celulaCurenta;
    }
    public Character getHero(){
        return character;
    }
    public void setLength(int length){
        this.length = length;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setMatrice(ArrayList <ArrayList <Cell >> matrice)
    {
        this.matrice=matrice;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    private Grid(){

    }
    public static Grid getInstance() {
        if (tabla == null)
            tabla = new Grid();
        return tabla;
    }
    public static boolean shopPlaces(int x, int y){
        return ((x==1 || x==tabla.getLength()-2)&&(y==1 || y==tabla.getWidth()-2));
    }
    public static ArrayList <ArrayList <Cell> > generateHardcodedGrid(){
        ArrayList <ArrayList <Cell> > Map = new ArrayList<>();
        for(int i=0; i<5; i++){
            ArrayList <Cell> AL = new ArrayList<>();
            for(int j=0; j<5; j++)
            {
                Cell a = new Cell();
                if((i<2 && j==3) || (i==2 && j==0))
                    a=a.generateRandomCellShop(i,j);
                else if(i==3 && j==4)
                    a=a.generateRandomCellEnemy(i,j);
                else if(i==4 && j==4)
                    a=a.generateRandomCellFinish(i,j);
                else
                    a=a.generateRandomCell(i,j);
                AL.add(a);
            }
            Map.add(AL);
        }
        return Map;
    }
    public static ArrayList <ArrayList <Cell> > GenerateGrid(int len, int wid){
        int i,j;
        if(len == 5 && wid == 5)
            return generateHardcodedGrid();
        ArrayList <ArrayList <Cell> > Map = new ArrayList<>();
        for(i=0;i<len;i++)
            {
                ArrayList <Cell> AL = new ArrayList<>();
                for(j=0;j<wid;j++)
                {
                    Cell a = new Cell();
                    if(i==len-1 && j==wid-1)
                        a=a.generateRandomCellFinish(i,j);
                    else if(shopPlaces(i,j))
                    {
                        a=a.generateRandomCellShop(i,j);
                    }
                    else {
                        Random rand = new Random();
                        int rand1 = rand.nextInt(len*wid);
                        if(rand1<=4)
                            a=a.generateRandomCellEnemy(i,j);
                        else
                            a=a.generateRandomCell(i,j);
                    }
                    AL.add(a);
                }
                Map.add(AL);
            }
        return Map;
    }
    public void goNorth(){
        if(celulaCurenta.x==0)
        {
            System.out.println("Can't move to the North!");
        }
        else {
            celulaCurenta.x -= 1;
            character.x-=1;
        }
    }
    public void goSouth(){
        if(celulaCurenta.x==width-1)
        {
            System.out.println("Can't move to the South!");
        }
        else {
            celulaCurenta.x += 1;
            character.x+=1;
        }
    }
    public void goWest(){
        if(celulaCurenta.y==0)
        {
            System.out.println("Can't move to the West!");
        }
        else {
            celulaCurenta.y -= 1;
            character.y-=1;

        }
    }
    public void goEast(){
        if(celulaCurenta.y==length-1)
        {
            System.out.println("Can't move to the East!");
        }
        else {
            character.y+=1;
            celulaCurenta.y += 1;
        }
    }
}
