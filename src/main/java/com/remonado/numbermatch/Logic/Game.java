package com.remonado.numbermatch.Logic;

import com.remonado.numbermatch.Tools.*;

public class Game{
    private Board board;

    public Game(int rows, int cols) {
        board = new Board(rows, cols);
    }
    public void undo(){

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
            if(current == null) return false;
            if(current == b){
                // generar movimiento
                return true;
            }
            if(current.getInfo().isActive() && !rightSearch) return false;
            else if(current.getInfo().isActive() && rightSearch) break;
        }
        if(rightSearch){
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
        }
        return false;
    }



    public boolean match(Node<Cell> a, Node<Cell> b) {
        if(!canMatch(a,b)) return false;
        a.getInfo().setActive(false);
        b.getInfo().setActive(false);
        board.linkNodes();
        return true;
    }


    public boolean hasWon() {

        return false;
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

    public Board getBoard() {
        return board;
    }
}
