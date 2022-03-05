package com.company;

public class Cell {
    public int x,y;
    private boolean visited;
    private CellEnum CT;  // cell Type
    public Cell(){
        this.x=0;
        this.y=0;
        this.visited=false;
        this.CT= CellEnum.EMPTY;
    }
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
    public CellEnum getCT(){
        return this.CT;
    }
    public void setCT(CellEnum ct){
        this.CT=ct;
    }
    public void setVisited() {
        this.visited = true;
    }
    public boolean isVisited(){
        return this.visited;
    }
    public Cell generateRandomCell(int x, int y){
        Cell randomCell = new Cell(x,y);
        randomCell.visited=false;
        randomCell.CT= CellEnum.EMPTY;
        return randomCell;
    }
    public Cell generateRandomCellEnemy(int x, int y){
        Cell randomCell = new Cell(x,y);
        randomCell.visited=false;
        randomCell.CT= CellEnum.ENEMY;
        return randomCell;
    }
    public Cell generateRandomCellShop(int x, int y){
        Cell randomCell = new Cell(x,y);
        randomCell.visited=false;
        randomCell.CT= CellEnum.SHOP;
        return randomCell;
    }
    public Cell generateRandomCellFinish(int x, int y){
        Cell randomCell = new Cell(x,y);
        randomCell.visited=false;
        randomCell.CT= CellEnum.FINISH;
        return randomCell;
    }
    public CellElement CE;
}
