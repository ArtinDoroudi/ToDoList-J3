package org.example.projectj3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DataVisualization extends Application {
    public void start(Stage stage){
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit");




        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);



        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");

        BorderPane borderPane = new BorderPane();
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