package org.example.projectj3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Adding extends Application {
    public void start(Stage stage){
        Button Create = new Button("Create New Task");
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(Create);
        borderPane.setTop(hbox);



        Scene scene = new Scene(borderPane, 1000, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
