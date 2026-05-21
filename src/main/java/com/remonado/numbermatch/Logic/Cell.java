package com.remonado.numbermatch.Logic;
import com.remonado.numbermatch.Tools.*;
public class Cell {
    private boolean isActive, isEnd, isBegin;
    private int number;
    private int col;
    private int row;
    public Cell(int number) {
        isActive = true;
        isEnd = false;
        isBegin = false;
        this.number = number;
        this.col = 0;
        this.row = 0;

    }
    public Cell(){
        isActive = true;
        isEnd = false;
        isBegin = false;
        number = 0;
        col = 0;
        row = 0;
    }


    /**
     * Returns whether input node contains same value or
     * adds up to 10.
     * @return true if nodes contain the same value or add up to 10
     */
    public boolean isMatchValue(Cell input) {
        return input.getNumber() == number || input.getNumber() + number == 10;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean isEnd() {
        return isEnd;
    }
    public void setEnd(boolean end) {
        isEnd = end;
    }
    public boolean isBegin() {
        return isBegin;

    }
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }
    public void setBegin(boolean begin) {
        isBegin = begin;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int value){
        number = value;
    }

}
