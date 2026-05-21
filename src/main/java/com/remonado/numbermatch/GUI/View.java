package com.remonado.numbermatch.GUI;

import com.remonado.numbermatch.Controller;
import com.remonado.numbermatch.Logic.*;
import com.remonado.numbermatch.Tools.Node;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View {

    private VBox root;
    private GridPane grid;
    private Controller controller;
    private Game game;
    private Button pista, undo, concordPend, concordEnc;
    private HBox buttons;
    public View(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        root.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);

        buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        pista = new Button("Pista");
        undo = new Button("Deshacer");
        concordPend = new Button("Concordancias pendientes");
        concordEnc = new Button("Concordancias Encontradas");
        buttons.getChildren().addAll(pista, undo, concordPend, concordEnc);
        root.getChildren().add(buttons);
        buttons.setSpacing(20);

        pista.setOnAction(e ->  controller.mostrarPista());
        undo.setOnAction(e -> controller.undo());
        concordPend.setOnAction(e -> controller.mostrarConcPend());
        concordEnc.setOnAction(e-> controller.mostrarConcEnc());
        //root.getStyleClass().add("root");

        //initializeCells();
        updateBoard();
    }

    public VBox getRoot() {
        return root;
    }

    public void updateBoard() {
        grid.getChildren().clear();
        grid.getStyleClass().add("game-grid");
        Node<Cell> current = game.getBoard().getCells().getInicioNode();
        int idx = 0;

        while (current != null) {
            Cell cell = current.getInfo();
            Button cellGraf = new Button(String.valueOf(cell.getNumber()));
            cellGraf.setPrefSize(65, 65);

            if (!cell.isActive()) {
                cellGraf.getStyleClass().add("cell-inactive");
                cellGraf.setDisable(true);
            } else {
                cellGraf.getStyleClass().add("cell-active");
            }

            int pos = idx;
            cellGraf.setOnAction(e -> controller.seleccionar(pos));
            grid.add(cellGraf,cell.getCol(),cell.getRow());
            current = current.getRight();
            idx++;
        }
    }
}