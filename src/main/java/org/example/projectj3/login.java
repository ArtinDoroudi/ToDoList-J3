package org.example.projectj3;

// Java final project
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class login extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Setting up gridpane
        GridPane grid = new GridPane();
        // Positioning it to center
        grid.setAlignment(Pos.CENTER);
        // Spacing horizontal and vertical between rows and columns
        grid.setHgap(10);
        grid.setVgap(10);

        // Set a tan background for the grid
        grid.setStyle("-fx-background-color: tan;");  // Tan background

        // Creating elements
        Label userName = new Label("Username:");
        userName.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14px;");
        grid.add(userName, 0, 1);

        TextField usernameEntered = new TextField();
        grid.add(usernameEntered, 1, 1);

        Label passwordEntered = new Label("Password:");
        passwordEntered.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14px;");
        grid.add(passwordEntered, 0, 2);

        PasswordField passwordTextfield = new PasswordField();
        grid.add(passwordTextfield, 1, 2);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #8cfa8c; -fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: black;");
        grid.add(loginButton, 1, 3);

        Label message = new Label();
        message.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        grid.add(message, 1, 4);
        message.setTextFill(Color.RED);

        // Button action for login
        loginButton.setOnAction(e -> {
            String username = usernameEntered.getText();
            String password = passwordTextfield.getText();

            if (username.isEmpty() || password.isEmpty()) {
                message.setText("Please enter both username and password.");
                message.setTextFill(Color.RED);  // Red text for error
            } else {
                message.setText("Login successful!");
                message.setTextFill(Color.GREEN);  // Green text for success
            }
        });

        // Set up the scene and stage
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
