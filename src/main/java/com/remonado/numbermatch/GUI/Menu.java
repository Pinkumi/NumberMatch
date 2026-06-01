package com.remonado.numbermatch.GUI;

import com.remonado.numbermatch.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

public class Menu {
    private VBox root;
    private Label tittle, rowsLabel, colsLabel;
    private Spinner<Integer> rows;
    private Spinner<Integer> cols;
    private Button inicio;
    Controller controller;

    public Menu(Controller controller){
        root = new VBox();
        this.controller = controller;
        root.setPrefSize(600, 600);
        tittle = new Label("NUMBER MATCH");
        rowsLabel = new Label("Ingrese las filas: ");
        colsLabel = new Label("Ingrese las columnas: ");
        rowsLabel.setStyle("-fx-font-size: 20;");
        colsLabel.setStyle("-fx-font-size: 20;");

        root.getChildren().add(tittle);
        inicio =  new Button("Iniciar");
        inicio.setOnAction(e -> {
            controller.changeRootView(cols.getValue(), rows.getValue());
        });
        inicio.setPrefSize(150, 40);
        tittle.setStyle("-fx-font-size: 45; -fx-font-family: 'Segoe UI'");
        rows = new Spinner<>(2,10, 5);

        cols = new Spinner<>(2,10,5);
        rows.setStyle("-fx-font-size: 20");
        cols.setStyle("-fx-font-size: 20");
        cols.setPrefSize(100, 50);
        rows.setPrefSize(100, 50);
        root.getChildren().addAll(rowsLabel,rows, colsLabel,cols);
        root.getChildren().add(inicio);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        inicio.getStyleClass().add("button-game");
    }
    public VBox getRoot(){
        return root;
    }
    public int getCols(){
        return cols.getValue();
    }
    public int getRows(){
        return rows.getValue();
    }



}
