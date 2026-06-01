package com.remonado.numbermatch.Logic;

import com.remonado.numbermatch.Tools.List;
import com.remonado.numbermatch.Tools.Pila;

public class Movement extends Pila {
    public Cell cell1;
    public Cell cell2;
    public boolean[] copia;
    public int copiaRows;

    public Movement(Cell cell1, Cell cell2){
        this.cell1 = cell1;
        this.cell2 = cell2;
    }
    public Cell getCell1(){ return cell1; }
    public Cell getCell2(){ return cell2; }
}