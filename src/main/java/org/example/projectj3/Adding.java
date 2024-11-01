package org.example.projectj3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Adding extends Application {
    public void start(Stage stage){
        Button Create = new Button("Create New Task");
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");

        Create.setPrefSize(300, 50);
        Create.setStyle("-fx-background-color: #00c500; -fx-border-color: rgba(3,0,0,0); -fx-font-size: 25; -fx-font-weight: bold;");
        Create.setOnAction(e -> System.out.println("Create New Task clicked!"));

        Dashboard.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(Create);
        borderPane.setTop(hbox);
        borderPane.setStyle("-fx-background-color: TAN");



        Scene scene = new Scene(borderPane, 1000, 500);

        stage.setTitle("Adding New Task");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
