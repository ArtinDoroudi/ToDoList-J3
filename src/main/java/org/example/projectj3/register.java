package org.example.projectj3;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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


        // label and field for username
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 0);

        // Password label and field
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        grid.add(confirmPasswordLabel, 0, 2);
        PasswordField confirmPasswordField = new PasswordField();
        grid.add(confirmPasswordField, 1, 2);

        // Register button
        Button registerButton = new Button("Register");
        grid.add(registerButton, 1, 3);

        // Register button action
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                alert(Alert.AlertType.INFORMATION, "Registration Successful", "Welcome, " + username + "!");
            } else {
                alert(Alert.AlertType.ERROR, "Registration Failed", "Passwords do not match. Please try again.");
            }
        });

        // Create and set scene
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //creating the alert method
    private void alert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
};

