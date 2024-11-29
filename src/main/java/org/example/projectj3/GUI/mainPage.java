package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        //creating all the nodes
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button createTaskButton = new Button("Create New Task");
        CheckBox cb = new CheckBox("Complete");
        cb.setIndeterminate(false);
        Text text = new Text("clean your room");
        Button update = new Button("Update");
        Button delete = new Button("Delete");

        //all styles for the nodes
        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        text.setStyle(" -fx-font-size: 17; -fx-font-weight: bold;");


        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);


        HBox taskRow = new HBox();
        taskRow.setAlignment(Pos.CENTER);
        taskRow.getChildren().addAll(cb, text, update, delete);
        taskRow.setStyle("-fx-background-color: LIGHTBLUE; -fx-background-radius: 10;");
        taskRow.setSpacing(20);
        taskRow.setMaxWidth(500);
        taskRow.setMinHeight(50);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(taskRow, createTaskButton );
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        borderPane.setStyle("-fx-background-color: TAN");


        Scene scene = new Scene(borderPane, 1000, 500);
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
