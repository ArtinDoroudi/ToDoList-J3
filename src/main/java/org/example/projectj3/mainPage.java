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
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: tan;"); // Set background color to tan

        // Button to read from database
        Button readButton = new Button("Read from Database");
        readButton.setStyle("-fx-font-weight: bold; -fx-background-color: #a1d3f4; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(readButton, 0, 0);

        // Button to delete from database
        Button deleteButton = new Button("Delete from Database");
        deleteButton.setStyle("-fx-font-weight: bold; -fx-background-color: #f4a1a1; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(deleteButton, 0, 1);


    public static void main(String[] args) {
        launch(args);
    }
}
