package com.remonado.numbermatch;

import com.remonado.numbermatch.GUI.*;
import com.remonado.numbermatch.Logic.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game(8,8);
        Controller controller = new Controller(game);
        View view = new View(controller, game);
        controller.setView(view);
        Scene scene = new Scene(view.getRoot(), 800, 700);
        scene.getStylesheets().add("file:src/main/resources/com/remonado/numbermatch/tablero.css");
        stage.setScene(scene);
        stage.setTitle("Number Match");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}