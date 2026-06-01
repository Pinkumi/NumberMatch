package com.remonado.numbermatch.GUI;

import com.remonado.numbermatch.Controller;
import com.remonado.numbermatch.Logic.*;
import com.remonado.numbermatch.Tools.Node;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View{

    private VBox gameRoot, root, menuRoot;
    private GridPane grid;
    private Controller controller;
    private Game game;
    private Stage stage;
    private Button pista, undo, concordPend, concordEnc, addFila;
    private HBox buttons;
    private boolean hayPista;
    private Cell pA, pB;
    private Menu menu;
    private Label concPendLab, concEncLab;
    public View(Controller controller, Game game) {
        menu = new Menu(controller);
        this.controller = controller;
        this.game = game;
        hayPista = false;
        pA = new Cell();
        pB = new Cell();
        gameRoot = new VBox();
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setSpacing(50);
        concPendLab = new Label("Pendientes: 0");
        concEncLab = new Label("Encontradas: 0");
        concPendLab.getStyleClass().add("popup-label");
        concEncLab.getStyleClass().add("popup-label");
        HBox stats = new HBox(40, concPendLab, concEncLab);
        stats.setAlignment(Pos.CENTER);
        gameRoot.getChildren().add(stats);
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        gameRoot.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);

        buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        pista = new Button("Pista");
        undo = new Button("Deshacer");
        addFila = new Button("Agregar Fila");
        concordPend = new Button("Concordancias pendientes");
        concordEnc = new Button("Concordancias Encontradas");
        buttons.getChildren().addAll(pista, undo, concordPend, concordEnc);
        gameRoot.getChildren().addAll(buttons, addFila);
        buttons.setSpacing(20);

        pista.setOnAction(e ->  controller.mostrarPista());
        undo.setOnAction(e -> controller.undo());
        concordPend.setOnAction(e -> controller.mostrarConcPend());
        concordEnc.setOnAction(e-> controller.mostrarConcEnc());

        addFila.setOnAction(e -> controller.agregarFila());
        menuRoot = menu.getRoot();
        root = menuRoot;
        pista.getStyleClass().add("button-game");
        undo.getStyleClass().add("button-game");
        concordPend.getStyleClass().add("button-game");
        concordEnc.getStyleClass().add("button-game");
        addFila.getStyleClass().add("button-game");

        //root.getStyleClass().add("root");

        //initializeCells();
       // updateBoard();
    }

    public VBox getRoot() {
        return root;
    }


    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void changeRootToGame(){
        root = gameRoot;
        stage.getScene().setRoot(gameRoot);
    }
    public void updateStats(){
        concPendLab.setText("Pendientes: " + game.getConcordPend());
        concEncLab.setText("Encontradas: " + game.getConcordEnc());
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
                if(hayPista && ((cell.getCol() == pA.getCol() && cell.getRow() == pA.getRow()) ||(cell.getCol() == pB.getCol() && cell.getRow() == pB.getRow()))){
                    cellGraf.getStyleClass().add("cell-pista");
                    Button btn = cellGraf;
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(ev -> {
                        btn.getStyleClass().remove("cell-pista");
                        btn.getStyleClass().add("cell-active");
                        hayPista = false;
                    });
                    pause.play();
                }
            }

            int pos = idx;
            cellGraf.setOnAction(e -> controller.seleccionar(pos));
            grid.add(cellGraf,cell.getCol(),cell.getRow());
            current = current.getRight();
            idx++;
        }
    }
    public void mostrarConcP(int val){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("CONCORDANCIAS PENDIENTES: " + val);
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:src/main/resources/com/remonado/numbermatch/tablero.css");
        scene.setOnKeyPressed(e -> wind.close());
        scene.setOnMouseClicked(e -> wind.close());
        wind.setScene(scene);
        wind.showAndWait();
    }
    public void mostrarConcE(int val){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("CONCORDANCIAS ENCONTRADAS: " + val);
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:src/main/resources/com/remonado/numbermatch/tablero.css");
        scene.setOnKeyPressed(e -> wind.close());
        scene.setOnMouseClicked(e -> wind.close());
        wind.setScene(scene);
        wind.showAndWait();
    }
    public void win(){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("GANASTE !!");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:src/main/resources/com/remonado/numbermatch/tablero.css");
        scene.setOnKeyPressed(e -> { wind.close(); volverAlMenu(); });
        scene.setOnMouseClicked(e -> { wind.close(); volverAlMenu(); });
        wind.setScene(scene);
        wind.showAndWait();
    }

    public void lose(){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("PERDISTE :C");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:src/main/resources/com/remonado/numbermatch/tablero.css");
        scene.setOnKeyPressed(e -> { wind.close(); volverAlMenu(); });
        scene.setOnMouseClicked(e -> { wind.close(); volverAlMenu(); });
        wind.setScene(scene);
        wind.showAndWait();
    }

    private void volverAlMenu(){
        stage.getScene().setRoot(menuRoot);
        root = menuRoot;
    }
    public void mostrarPistas(Cell pA, Cell pB){
        hayPista = true;
        this.pA = pA;
        this.pB = pB;
        updateBoard();
    }
}