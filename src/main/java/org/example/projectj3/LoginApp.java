package org.example.projectj3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class LoginApp extends Application {
    private Stage window;
    private Scene loginScene, welcomeScene;
    private User loggedInUser;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Login System");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label loginStatus = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            loggedInUser = authenticate(username, password);
            if (loggedInUser != null) {
                loginStatus.setText("Login successful!");
                showWelcomePage();
            } else {
                loginStatus.setText("Invalid credentials, try again.");
            }
        });

        VBox loginLayout = new VBox(10, usernameField, passwordField, loginButton, loginStatus);
        loginScene = new Scene(loginLayout, 300, 200);
        window.setScene(loginScene);
        window.show();
    }

    private void showWelcomePage() {
        Label welcomeLabel = new Label("Welcome, " + loggedInUser.getUsername() + "!");
        Label premiumStatusLabel = new Label("Premium Status: " + (loggedInUser.isPremium() ? "Yes" : "No"));
        Button logoutButton = new Button("Logout");

        logoutButton.setOnAction(e -> {
            loggedInUser = null; // Log out the user
            window.setScene(loginScene);
        });

        VBox welcomeLayout = new VBox(10, welcomeLabel, premiumStatusLabel, logoutButton);
        welcomeScene = new Scene(welcomeLayout, 300, 200);
        window.setScene(welcomeScene);
    }

    // we have to replace it with db later, just wanted to pracitce with file handling for future use
    private User authenticate(String username, String password) {
        URL resource = getClass().getClassLoader().getResource("users.txt");
        if (resource == null) {
            System.out.println("File not found!");
            return null;
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] credentials = line.split(":");
                    if (credentials.length == 3 && credentials[0].equals(username) && credentials[1].equals(password)) {
                        boolean isPremium = Boolean.parseBoolean(credentials[2]);
                        return new User(username, password, isPremium);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading users file.");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
