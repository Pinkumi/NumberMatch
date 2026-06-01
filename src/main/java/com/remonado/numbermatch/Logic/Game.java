package com.remonado.numbermatch.Logic;

import com.remonado.numbermatch.Controller;
import com.remonado.numbermatch.Tools.*;

import java.util.ArrayList;
import java.util.Collections;

public class Game{
    private Board board;
    private Pila<Movement> movementStack;
    private boolean gameover;
    private int concordPend;
    private int concordEnc;
    private boolean mostrarPista = false;
    private Cell pA;
    private Cell pB;
    public Game(int rows, int cols) {
        board = new Board(rows, cols);
        board.generateBoard();
        movementStack = new Pila<>(500);
        concordEnc = 0;
        concordPend = 0;
        gameover = false;
    }
    public Game(){
        movementStack = new Pila<>(500);
        concordEnc = 0;
        concordPend = 0;
        gameover = false;
    }
    public void setDimensions(int rows, int cols){
        board = new  Board(rows, cols);
        board.generateBoard();
    }
    public void undo(){
        if(movementStack.pila_vacia()) return;
        Movement movement = movementStack.pop();
        board.setRows(movement.copiaRows);
        Node<Cell>[] all = board.getAllNodes();
        int size = movement.copia.length;
        for(int i = 0; i < size - 1; i++){
            all[i].setRight(all[i+1]);
        }
        all[size-1].setRight(null);
        board.getCells().setInicio(all[0]);

        for(int i = 0; i < size; i++) {
            all[i].getInfo().setActive(movement.copia[i]);
        }
        concordEnc--;
        board.linkNodes();
        calculateConcPend();
        gameover = false;
    }
    public void addRow(){
        int cols = board.getCols();
        ArrayList<Integer> numeros = new ArrayList<>();
        Node<Cell> current = board.getCells().getInicioNode();
        while(current != null){
            if(current.getInfo().isActive())
                numeros.add(current.getInfo().getNumber());
            current = current.getRight();
        }
        int cantidad = Math.min(cols, numeros.size());
        if(cantidad % 2 != 0) cantidad--;
        if(cantidad == 0) return;
        ArrayList<Integer> fila = new ArrayList<>(numeros.subList(0, cantidad));
        Collections.shuffle(fila);
        for(int i = 0; i < fila.size(); i++){
            Cell cell = new Cell(fila.get(i));
            if(i == 0) cell.setBegin(true);
            if(i == fila.size()-1) cell.setEnd(true);
            board.getCells().insertarFinal(cell);
        }
        board.rebuildAllNodes();
        board.setRows(board.getRows() + 1);
        board.linkNodes();
        calculateConcPend();
    }
    public boolean canMatch(Node<Cell> a, Node<Cell> b) {

        if(a.isNeighbor(b) && a.getInfo().isMatchValue(b.getInfo())){
            //generar movimiento
            return true;
        }
        if(a == null || b == null || a == b || (!a.getInfo().isActive() || !b.getInfo().isActive())|| (!a.getInfo().isMatchValue(b.getInfo()))){
            return false;
        }

        boolean canMatch = false;
        String dir = getDirection(a,b);
        if(dir == null) return false;
        boolean rightSearch = (dir.equals("down") ||  dir.equals("downLeft") ||  dir.equals("downRight"));
//        if(rightSearch)System.out.println("Se buscara tambien por la derecha");
        Node<Cell> current = a;
        while(current != null){
            switch(dir){
                case "left":
                    current = current.getLeft();
                    break;
                case "right":
                    current = current.getRight();
                    break;
                case "up":
                    current = current.getUp();
                    break;
                case "upLeft":
                    current =current.getUpLeft();
                    break;
                case "upRight":
                    current=current.getUpRight();
                    break;
                case "down":
                    current =current.getDown();
                    break;
                case "downLeft":
                    current =current.getDownLeft();
                    break;
                case "downRight":
                    current =current.getDownRight();
                    break;
            }
            if(current == null) break;
            if(current == b){
                // generar movimiento
                return true;
            }
            if(current.getInfo().isActive() && !rightSearch) return false;
            else if(current.getInfo().isActive() && rightSearch) break;
        }
        //if(rightSearch){
        current = a;
        while(current != null){
            current = current.getRight();
            if(current == null) return false;
            if(current == b){
                //generar movimiento
                return true;
            }
            if(current.getInfo().isActive()) return false;
        }
        //}
        return false;
    }


    public boolean match(Node<Cell> a, Node<Cell> b) {
        if(!canMatch(a,b)) return false;

        boolean[] snapshot = new boolean[board.getCells().getSize()];
        for(int i = 0; i < snapshot.length; i++)
            snapshot[i] = ((Cell)board.getCells().get(i).getInfo()).isActive();

        Movement movement = new Movement(a.getInfo(), b.getInfo());
        movement.copia = snapshot;
        movement.copiaRows = board.getRows();
        a.getInfo().setActive(false);
        b.getInfo().setActive(false);
        movementStack.push(movement);
        concordEnc++;
        board.linkNodes();
        calculateConcPend();
        if(concordPend == 0 && !hasWon()) gameover = true;
        return true;
    }
    public boolean getGameOver(){
        return gameover;
    }
    public int calculateConcPend(){
        concordPend = 0;
        pA = null;
        pB = null;
        int size = board.getCells().getSize();
        for(int i = 0; i < size; i++){
            Node<Cell> a = board.getCells().get(i);
            if(a == null) continue;
            if(!a.getInfo().isActive()) continue;
            for(int j = i + 1; j < size; j++){
                Node<Cell> b = board.getCells().get(j);
                if(b == null) continue;
                if(!b.getInfo().isActive()) continue;
                if(canMatch(a, b)) {
                    concordPend++;
                    if(pA == null) {
                        pA = a.getInfo();
                        pB = b.getInfo();
                    }
                }
            }
        }

        return concordPend;
    }
    public boolean hasWon() {
        Node<Cell> current = board.getCells().getInicioNode();
        while(current != null){
            if(current.getInfo().isActive())
                return false;
            current = current.getRight();
        }
        System.out.println("GANASTE");
        return true;
    }

    public String getDirection(Node<Cell> n1, Node<Cell> n2){
        int r1 = n1.getInfo().getRow();
        int c1 = n1.getInfo().getCol();
        int r2 = n2.getInfo().getRow();
        int c2 = n2.getInfo().getCol();
        if(n1==n2 || ((r2 == r1)&&(c2 == c1))) return null;
        else if(r1<r2){
            if(c1==c2) return "down";
            if(c1>c2) return "downLeft";
            if(c1<c2) return "downRight";

        }else if(r1==r2){
            if(c1>c2) return "left";
            if(c1<c2) return "right";

        }else{
            if(c1>c2) return "upLeft";
            if(c1<c2) return "upRight";
            if(c1==c2) return "up";
        }
        return null;
    }
    public int getConcordPend(){
        return concordPend;
    }
    public int getConcordEnc(){
        return concordEnc;
    }
    public Board getBoard() {
        return board;
    }
    public Cell[] getPista(){
        mostrarPista = true;
        return new Cell[]{pA, pB};
    }
    public  void setMostrarPista(boolean b){
        mostrarPista = b;
    }
}
