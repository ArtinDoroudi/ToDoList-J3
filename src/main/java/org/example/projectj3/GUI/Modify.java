package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Modify extends Application {
    public void start(Stage stage){
        Text UpdateTask = new Text("Update Task");
        Text UpdateDescription = new Text("Update Description");
        Text Date = new Text("Date");
        Text tag = new Text("Tag");
        TextArea UpdateTaskName = new TextArea("Update your task Here");
        TextArea UpdateTaskDescription = new TextArea(" Update Description");
        DatePicker dueDate = new DatePicker();
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit");
        ComboBox Tags = new ComboBox();

        UpdateTask.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        UpdateDescription.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        tag.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Date.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");


        UpdateTaskName.setMaxSize(500, 50);
        UpdateTaskDescription.setMaxSize(500, 150);



        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(UpdateTask,UpdateTaskName,UpdateDescription,UpdateTaskDescription,Date, dueDate, tag, Tags, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);

        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        borderPane.setStyle("-fx-background-color: TAN");



        Scene scene = new Scene(borderPane, 1000, 500);

        stage.setTitle("Update Task");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}