package org.example.projectj3.GUI;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        Image updateicon = new Image(getClass().getResource("/images/update3.jpg").toExternalForm());
        ImageView updateview = new ImageView(updateicon);
        Image trashcan = new Image(getClass().getResource("/images/trashcan.jpg").toExternalForm());
        ImageView trash = new ImageView(trashcan);
        updateview.setFitWidth(50);
        updateview.setFitHeight(20);
        trash.setFitWidth(25);
        trash.setFitHeight(35);


        Button update = new Button();
        Button delete = new Button();
        update.setGraphic(updateview);
        delete.setGraphic(trash);

        //all styles for the nodes
        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        text.setStyle(" -fx-font-size: 17; -fx-font-weight: bold;");

        Dashboard.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Dashboard);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        Dashboard.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Dashboard);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
        About.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), About);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        About.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), About);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
        Help.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Help);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        Help.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), Help);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });


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
