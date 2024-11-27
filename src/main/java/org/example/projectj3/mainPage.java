package org.example.projectj3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with buttons and welcome label
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);

        Button dashboardButton = new Button("Dashboard");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Label welcomeLabel = new Label("Welcome");

        // Apply styling to buttons and label
        dashboardButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        aboutButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        helpButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        welcomeLabel.setStyle("-fx-text-fill: black; -fx-font-size: 23; -fx-font-weight: bold;");


        // Task section with centered layout
        VBox taskSection = new VBox(15);
        taskSection.setPadding(new Insets(10));
        taskSection.setAlignment(Pos.CENTER);

        Button createTaskButton = new Button("Create New Task");
        createTaskButton.setOnAction(e -> System.out.println("Create New Task clicked"));
        createTaskButton.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold;");
        taskSection.getChildren().add(createTaskButton);

        // Create Task Rows (Each row has a "Complete" button, TextField, Save and Delete buttons)
        for (int i = 1; i <= 3; i++) {
            int taskIndex = i;

            HBox taskRow = new HBox(20);
            taskRow.setAlignment(Pos.CENTER);

            // "Complete" button on the left side
            Button completeButton = new Button("Complete");
            completeButton.setStyle("-fx-background-color: #ff704d; -fx-font-size: 13; -fx-font-weight: bold;");
            completeButton.setOnAction(e -> System.out.println("Task " + taskIndex + " marked as complete"));

            TextField taskField = new TextField("Enter Task Here " + taskIndex);
            taskField.setPrefWidth(200);

            Button saveButton = new Button("Save");
            Button deleteButton = new Button("Delete");

            saveButton.setOnAction(e -> System.out.println("Saved Task " + taskIndex + ": " + taskField.getText()));
            deleteButton.setOnAction(e -> System.out.println("Delete Task " + taskIndex + " clicked"));

            taskRow.getChildren().addAll(completeButton, taskField, saveButton, deleteButton);
            taskSection.getChildren().add(taskRow);
        }

        // Main layout with the top bar and task section
        VBox mainLayout = new VBox(20, topBar, taskSection);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: tan;");

        // Scene and stage with updated size
        Scene scene = new Scene(mainLayout, 1000, 500);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
