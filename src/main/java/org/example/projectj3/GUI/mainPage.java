package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainPage extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");

        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);


        Button createTaskButton = new Button("Create New Task");



        // Create Task Rows (Each row has a "Complete" button, TextField, Save and Delete buttons)




            CheckBox cb = new CheckBox("Complete");
            cb.setIndeterminate(false);



            Text text = new Text("clean your room");
            text.setStyle(" -fx-font-size: 17; -fx-font-weight: bold;");

            Button update = new Button("Update");
            Button delete = new Button("Delete");


        HBox taskRow = new HBox(20);
        taskRow.setAlignment(Pos.CENTER);
        taskRow.getChildren().addAll(cb, text, update, delete);
        taskRow.setStyle("-fx-background-color: LIGHTBLUE");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(taskRow, createTaskButton );
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        // Main layout with the top bar and task section

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        borderPane.setStyle("-fx-background-color: TAN");

        // Scene and stage with updated size
        Scene scene = new Scene(borderPane, 1000, 500);
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void setLoggedInUser(String username) {
    }
}
