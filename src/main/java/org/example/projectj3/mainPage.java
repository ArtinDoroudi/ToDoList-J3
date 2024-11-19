package org.example.projectj3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Header with buttons and label
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);

        Button dashboardButton = new Button("Dashboard");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Label welcomeLabel = new Label("Welcome");
        // Push Welcome label to the right

        HBox.setHgrow(welcomeLabel, Priority.ALWAYS);
        welcomeLabel.setStyle("-fx-text-fill: white;");

        header.getChildren().addAll(dashboardButton, aboutButton, helpButton, welcomeLabel);
        header.setStyle("-fx-background-color: #336699;");

        // Task Section
        VBox taskButtons = new VBox(10);
        taskButtons.setPadding(new Insets(10));

        Button createTaskButton = new Button("Create New Task");
        createTaskButton.setOnAction(e -> System.out.println("Create New Task clicked"));

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
