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

public class MainPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a horizontal box (HBox) for the header section
        HBox header = new HBox(10); // 10px spacing between elements in the HBox
        header.setPadding(new Insets(10)); // Set padding around the HBox
        header.setAlignment(Pos.CENTER_LEFT); // Align the elements to the left

        // Create buttons for the header (Dashboard, About, Help)
        Button dashboardButton = new Button("Dashboard");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");

        // Create a label for "Welcome" in the header
        Label welcomeLabel = new Label("Welcome");
        HBox.setHgrow(welcomeLabel, Priority.ALWAYS); // Push the label to the far right
        welcomeLabel.setStyle("-fx-text-fill: white;"); // Set the label's text color to white

        // Add the buttons and label to the header
        header.getChildren().addAll(dashboardButton, aboutButton, helpButton, welcomeLabel);
        header.setStyle("-fx-background-color: #336699;"); // Set the background color of the header

        // Create a vertical box (VBox) for task buttons
        VBox taskButtons = new VBox(10); // 10px spacing between elements in the VBox
        taskButtons.setPadding(new Insets(10)); // Set padding around the VBox

        // Create a "Create New Task" button and add it to the VBox
        Button createTaskButton = new Button("Create New Task");
        createTaskButton.setOnAction(e -> System.out.println("Create New Task clicked")); // Add click event
        taskButtons.getChildren().add(createTaskButton); // Add the button to the VBox

        // Loop to create existing task buttons with edit and delete options
        for (int i = 1; i <= 3; i++) {
            int taskIndex = i; // Create a final variable to use inside the lambda expressions

            // Create a horizontal box (HBox) for each task row
            HBox taskRow = new HBox(10); // 10px spacing between elements in the row
            taskRow.setAlignment(Pos.CENTER_LEFT); // Align the elements to the left

            // Create a task button with a label "Enter Task Here {i}"
            Button taskButton = new Button("Enter Task Here " + taskIndex);

            // Create "Edit" and "Delete" buttons for the task
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            // Add click event for the "Edit" button
            editButton.setOnAction(e -> System.out.println("Edit Task " + taskIndex + " clicked"));

            // Add click event for the "Delete" button
            deleteButton.setOnAction(e -> System.out.println("Delete Task " + taskIndex + " clicked"));

            // Add the task button, edit button, and delete button to the task row
            taskRow.getChildren().addAll(taskButton, editButton, deleteButton);

            // Add the task row to the VBox
            taskButtons.getChildren().add(taskRow);
        }
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
