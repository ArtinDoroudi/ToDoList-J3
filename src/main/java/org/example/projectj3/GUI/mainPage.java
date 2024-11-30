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
import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;

import java.sql.Connection;
import java.util.List;

public class mainPage extends Application {
    private VBox taskContainer;

    @Override
    public void start(Stage primaryStage) {
        // Example of loading tasks after the main page starts
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        int userId = 1;  // Example: assume you're using a fixed userId
        List<Task> tasks = Task.getTasksByUserId(userId, connection);
        for (Task task : tasks) {
            addNewTask(task.getTitle()); // Call to add the task to the UI dynamically
        }

        //creating all the nodes
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button createTaskButton = new Button("Create New Task");
        CheckBox cb = new CheckBox("Complete");
        CheckBox cb2 = new CheckBox("Complete");
        Text text = new Text("clean your room");
        Text text2 = new Text("finish java");

        Image updateicon = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview = new ImageView(updateicon);
        Image trashcan = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash = new ImageView(trashcan);
        updateview.setFitWidth(30);
        updateview.setFitHeight(30);
        trash.setFitWidth(30);
        trash.setFitHeight(30);

        Image updateicon2 = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview2 = new ImageView(updateicon2);
        Image trashcan2 = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash2 = new ImageView(trashcan2);
        updateview2.setFitWidth(30);
        updateview2.setFitHeight(30);
        trash2.setFitWidth(30);
        trash2.setFitHeight(30);


        Button update = new Button();
        Button delete = new Button();
        update.setGraphic(updateview);
        delete.setGraphic(trash);

        Button update2 = new Button();
        Button delete2 = new Button();
        update2.setGraphic(updateview2);
        delete2.setGraphic(trash2);

        taskContainer = new VBox(); // Container for tasks
        taskContainer.setAlignment(Pos.CENTER);
        taskContainer.setSpacing(10);

        createTaskButton.setOnAction(event -> {
            try {
                Adding addingPage = new Adding(); // No parameters passed
                Stage stage = (Stage) createTaskButton.getScene().getWindow();
                addingPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error loading the Adding page.");
            }
        });



        //all styles for the nodes
        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        text.setStyle(" -fx-font-size: 17; -fx-font-weight: bold;");
        text2.setStyle(" -fx-font-size: 17; -fx-font-weight: bold;");

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

        HBox taskRow2 = new HBox();
        taskRow2.setAlignment(Pos.CENTER);
        taskRow2.getChildren().addAll(cb2, text2, update2, delete2);
        taskRow2.setStyle("-fx-background-color: LIGHTGREY; -fx-background-radius: 10;");
        taskRow2.setSpacing(20);
        taskRow2.setMaxWidth(500);
        taskRow2.setMinHeight(50);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(taskRow, taskRow2, createTaskButton, taskContainer );
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
    public void addNewTask(String task_title) {
        CheckBox checkBox = new CheckBox("Complete");
        Button updateButton = new Button();
        Button deleteButton = new Button();

        Image updateIcon = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateView = new ImageView(updateIcon);
        Image trashCan = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash = new ImageView(trashCan);

        updateView.setFitWidth(30);
        updateView.setFitHeight(30);
        trash.setFitWidth(30);
        trash.setFitHeight(30);

        updateButton.setGraphic(updateView);
        deleteButton.setGraphic(trash);

        // Add the task row to the main page
        addTaskRow(task_title, checkBox, updateButton, deleteButton, "LIGHTBLUE");
    }




    // Method to dynamically add a task row
    public void addTaskRow(String task_title, CheckBox checkBox, Button updateButton, Button deleteButton, String color) {
        HBox taskRow = new HBox();
        taskRow.setStyle("-fx-background-color: " + color + "; -fx-padding: 10;");
        taskRow.setSpacing(10);

        taskRow.getChildren().addAll(new Label(task_title), checkBox, updateButton, deleteButton);

        taskContainer.getChildren().add(taskRow);
    }


    public static void main(String[] args) {
        launch(args);
    }
}


