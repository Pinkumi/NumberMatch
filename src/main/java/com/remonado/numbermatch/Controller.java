package com.remonado.numbermatch;

import com.remonado.numbermatch.GUI.View;
import com.remonado.numbermatch.Logic.*;
import com.remonado.numbermatch.Tools.Node;

public class Controller {

    private Game game;
    private View view;
    private Node<Cell> firstSelected;

    public Controller(Game game) {
        this.game = game;
    }

    public void setView(View view) {
        this.view = view;
    }
    public void changeRootView(int cols, int rows){
        view.changeRootToGame();
        game.setDimensions(cols, rows);
        view.updateBoard();
        view.updateStats();
    }
    public void agregarFila(){
        game.addRow();
        view.updateBoard();
        view.updateStats();
    }
    public void seleccionar(int pos) {
        Node<Cell> selected = game.getBoard().getCells().get(pos);
        if(selected == null)
            return;
        if(firstSelected == null) {
            firstSelected = selected;
            System.out.println("Primer nodo seleccionado");
        } else {
            boolean success = game.match(firstSelected, selected);
            if(success) {
                System.out.println("MATCH");

            } else {
                System.out.println("NO MATCH");
            }
            firstSelected = null;
            game.getBoard().linkNodes();
            view.updateBoard();
            view.updateStats();
            if(game.hasWon()) view.win();
            if(game.getGameOver()) view.lose();
        }
    }

    public void mostrarPista() {
        view.mostrarPistas(game.getPista()[0], game.getPista()[1]);
    }
    public void mostrarConcPend() {
        view.mostrarConcP(game.calculateConcPend());
    }
    public void resetPista(){
        game.setMostrarPista(false);
    }
    public void mostrarConcEnc(){
        view.mostrarConcE(game.getConcordEnc());
    }
    public void undo(){
        game.undo();
        view.updateBoard();
        view.updateStats();
    }

}