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
                firstSelected.getInfo().setActive(false);
                selected.getInfo().setActive(false);
                System.out.println("MATCH");
            } else {
                System.out.println("NO MATCH");
            }
            firstSelected = null;
            game.getBoard().linkNodes();
            view.updateBoard();
        }
    }
    public void mostrarPista() {

    }
    public void mostrarConcPend() {

    }
    public void mostrarConcEnc(){

    }
    public void undo(){
        game.undo();
        view.updateBoard();
    }

}