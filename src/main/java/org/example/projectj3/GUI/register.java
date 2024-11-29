package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class register extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Register Page");

        // Create grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER); // Center alignment
        grid.setStyle("-fx-background-color: tan;");  // Tan background

        // Label and field for username
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14px;");
        grid.add(usernameLabel, 0, 0);

        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 0);

        // Password label and field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14px;");
        grid.add(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14px;");
        grid.add(confirmPasswordLabel, 0, 2);

        PasswordField confirmPasswordField = new PasswordField();
        grid.add(confirmPasswordField, 1, 2);

        // Register button
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #8cfa8c; -fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(registerButton, 1, 3);

        // Message label for success/error
        Label message = new Label();
        message.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        grid.add(message, 1, 4);

        // Register button action
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                message.setText("Registration Successful!");
                message.setTextFill(Color.GREEN); // Green for success
            } else {
                message.setText("Passwords do not match. Please try again.");
                message.setTextFill(Color.RED); // Red for error
            }
        });

        // Create and set scene
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
