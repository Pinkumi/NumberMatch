package com.remonado.numbermatch.Logic;

import com.remonado.numbermatch.Tools.List;

public class Movement {
    public Cell cell1;
    public Cell cell2;
    public boolean seQuitoFIla;
    public List<Cell> cells1;
    public List<Cell> cells2;

    public Movement(Cell cell1, Cell cell2){
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    public void seQuitoFila(){
        seQuitoFIla = true;
    }
    public Cell getCell1(){
        return cell1;
    }
    public Cell getCell2(){
        return cell2;
    }
    public boolean getSeQuitoFila(){
        return seQuitoFIla;
    }
    public void setCell1(Cell cell1){
        this.cell1 = cell1;
    }
    public void setCell2(Cell cell2){
        this.cell2 = cell2;
    }
    public List<Cell> getCells1(){
        return cells1;
    }
    public List<Cell> getCells2(){
        return cells2;
    }
    public void setCells1(List<Cell> cells1){
        this.cells1 = cells1;
    }
    public void setCells2(List<Cell> cells2){
        this.cells2 = cells2;
    }




}
