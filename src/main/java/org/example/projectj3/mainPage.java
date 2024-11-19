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

       

        // Button to read from database
        Button readButton = new Button("Read from Database");
        readButton.setStyle("-fx-font-weight: bold; -fx-background-color: #a1d3f4; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(readButton, 0, 0);

        // Button to delete from database
        Button deleteButton = new Button("Delete from Database");
        deleteButton.setStyle("-fx-font-weight: bold; -fx-background-color: #f4a1a1; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(deleteButton, 0, 1);

        // Button to sign out
        Button signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-font-weight: bold; -fx-background-color: #f2f2f2; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(signOutButton, 0, 2);

        // Event handling for Read button
        readButton.setOnAction(event -> {
            // Mock database read operation
            showAlert(Alert.AlertType.INFORMATION, "Database Read", "Data read from the database successfully!");
        });

        // Event handling for Delete button
        deleteButton.setOnAction(event -> {
            // Mock database delete operation
            showAlert(Alert.AlertType.WARNING, "Database Delete", "Data deleted from the database successfully!");
        });

        // Event handling for Sign Out button
        signOutButton.setOnAction(event -> {
            // Go back to the login page or close the application
            showAlert(Alert.AlertType.INFORMATION, "Sign Out", "You have been signed out.");
            primaryStage.close();
        });

        // Create and set the scene
        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
