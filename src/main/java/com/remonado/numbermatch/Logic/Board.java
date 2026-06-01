package com.remonado.numbermatch.Logic;
import com.remonado.numbermatch.Tools.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board{

    private int rows;
    private int cols;
    private boolean isRowDeleted;
    private int nRowDeleted;
    private List cells;
    private Node<Cell>[] allNodes;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new List();
    }
    public int generateBoard() {
        Random randNum = new Random();
        ArrayList<Cell> celdasProv = new ArrayList();
        for(int i = 0; i < (rows*cols)/2; i++) {
            int val = randNum.nextInt(9)+1;
            celdasProv.add(new Cell(val));
            celdasProv.add(new Cell(val));
        }
        Collections.shuffle(celdasProv);
        for(int i = 0; i < celdasProv.size(); i++) {
            if(i % cols == 0) celdasProv.get(i).setBegin(true);
            else if(i % cols == cols-1) celdasProv.get(i).setEnd(true);
            cells.insertarFinal(celdasProv.get(i));
        }

        int total = celdasProv.size();
        allNodes = new Node[total];
        Node<Cell> cur = cells.getInicioNode();
        for(int i = 0; i < total; i++){
            allNodes[i] = cur;
            cur = cur.getRight();
        }

        linkNodes();
        return (rows*cols)/2;
    }
    public void rebuildAllNodes(){
        int size = cells.getSize();
        allNodes = new Node[size];
        Node<Cell> cur = cells.getInicioNode();
        for(int i = 0; i < size; i++){
            allNodes[i] = cur;
            cur = cur.getRight();
        }
    }
    public void linkNodes(){
        int size = cells.getSize();
        int cantPorFila = 0;
        boolean rowStart = true;
        for(int i = 0; i < size; i++){
            Node<Cell> current = cells.get(i);
            int row =i / cols;
            int col =i % cols;
            if(col == 0) cantPorFila=0;

            if(col>0){current.setLeft(cells.get(i - 1));}
            if(row>0){current.setUp(cells.get(i-cols));}
            if(row<rows-1){current.setDown(cells.get(i + cols));}
            if(row>0 && col > 0) current.setUpLeft(cells.get(i - cols - 1));
            if(row>0 && col < cols-1) current.setUpRight(cells.get(i - cols + 1));
            if(row<rows-1 && col>0) current.setDownLeft(cells.get(i + cols - 1));
            if(row<rows-1 && col<cols-1) current.setDownRight(cells.get(i + cols + 1));

            current.getInfo().setPos(row,col);

            if(current.getInfo().isActive()) cantPorFila++;

            if(col == cols-1){
                if(cantPorFila==0){
                    int inicioFila = i - (cols - 1);
                    int finFila = i;
                    Node<Cell> ant = null;
                    Node<Cell> sig = null;
                    if(inicioFila > 0) ant = cells.get(inicioFila - 1);
                    if(finFila < size - 1) sig = cells.get(finFila + 1);
                    if(ant != null) ant.setRight(sig);
                    else cells.setInicio(sig);
                    rows--;
                    linkNodes();
                    current.getInfo().setPos(row,col);
                    return;
                }
            }

        }
    }


    public List getCells() {
        return cells;
    }
    public void setRows(int rows){
        this.rows = rows;
    }
    public int getRows() {
        return rows;
    }
    public Node<Cell>[] getAllNodes(){ return allNodes; }

    public int getCols() {
        return cols;
    }
}