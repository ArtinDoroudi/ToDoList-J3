package org.example.projectj3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class loginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Setting up gridpane
        GridPane grid = new GridPane();
        //positionning it to center
        grid.setAlignment(Pos.CENTER);
        //spacing horzontal and vertical between rows and column 
        grid.setHgap(10);
        grid.setVgap(10);

        // Creating elements
        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField usernameEntered = new TextField();
        grid.add(usernameEntered, 1, 1);

        Label passwordEntered = new Label("Password:");
        grid.add(passwordEntered, 0, 2);

        PasswordField passwordTextfield = new PasswordField();
        grid.add(passwordTextfield, 1, 2);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);

        Label message = new Label();
        grid.add(message, 1, 4);
        message.setTextFill(Color.RED);

        // Button action for login
        loginButton.setOnAction(e -> {
            String username = usernameEntered.getText();
            String password = passwordTextfield.getText();

            if (username.isEmpty() || password.isEmpty()) {
                message.setText("Please enter both username and password.");
            } else {
                message.setTextFill(Color.GREEN);
                message.setText("Login successful!");
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