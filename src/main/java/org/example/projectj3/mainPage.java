package org.example.projectj3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class mainPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Page");

        // Create grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(20);
        grid.setHgap(20);

    public static void main(String[] args) {
        launch(args);
    }
}
