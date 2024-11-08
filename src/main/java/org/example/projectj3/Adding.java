package org.example.projectj3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Adding extends Application {
    public void start(Stage stage){
        Text Task = new Text("Task");
        Text Description = new Text("Description");
        TextArea TaskName = new TextArea("Add your new task Here");
        TextArea TaskDescription = new TextArea("Description");
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");

        Task.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");

        Description.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");


        TaskName.setMaxSize(500, 50);
        TaskDescription.setMaxSize(500, 200);



        Dashboard.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Translecent; -fx-border-color: Translecent; -fx-font-size: 20; -fx-font-weight: bold;");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(Task,TaskName,Description,TaskDescription);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
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
