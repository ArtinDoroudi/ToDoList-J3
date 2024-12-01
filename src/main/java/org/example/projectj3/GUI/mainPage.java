package org.example.projectj3.GUI;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainPage extends Application {
    private VBox taskList;

    @Override
    public void start(Stage primaryStage) {
        //creating all the nodes
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button createTaskButton = new Button("Create New Task");


        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");


        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        taskList = new VBox();
        taskList.setAlignment(Pos.CENTER);
        taskList.setSpacing(15);



        createTaskButton.setOnAction(e -> {
            Stage addingStage = new Stage();
            Adding addingPage = new Adding();
            addingPage.start(addingStage);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(taskList, createTaskButton );
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);


        CheckBox cb = new CheckBox("Complete");
        CheckBox cb2 = new CheckBox("Complete");

        Text text = new Text("clean your room");
        Text text2 = new Text("finish java");


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


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        borderPane.setStyle("-fx-background-color: TAN");


        Scene scene = new Scene(borderPane, 1000, 500);
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private HBox addTaskRow(String Title, String description) {
        CheckBox cb = new CheckBox("Complete");
        Text taskTitleText = new Text(Title);
        Text taskDescription = new Text(description);

        Image updateicon2 = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview2 = new ImageView(updateicon2);
        Image trashcan2 = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash2 = new ImageView(trashcan2);
        updateview2.setFitWidth(30);
        updateview2.setFitHeight(30);
        trash2.setFitWidth(30);
        trash2.setFitHeight(30);

        Button update = Update("/images/update4.png");
        Button delete = Delete("/images/bin.png");

        HBox taskRow = new HBox(cb, taskTitleText, update, delete
        );
        taskRow.setStyle("-fx-background-color: LIGHTBLUE; -fx-background-radius: 10;");
        taskRow.setAlignment(Pos.CENTER);
        taskRow.setSpacing(15);
        taskRow.setMaxWidth(700);
        taskRow.setMinHeight(50);

        return taskRow;
    }
    private Button Delete(String path) {
        Image trashcan2 = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash2 = new ImageView(trashcan2);
        trash2.setFitWidth(30);
        trash2.setFitHeight(30);
        Button delete = new Button();
        delete.setGraphic(trash2);
        return delete;
    }

    private Button Update(String path) {
        Image updateicon2 = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview2 = new ImageView(updateicon2);
        updateview2.setFitWidth(30);
        updateview2.setFitHeight(30);
        Button update = new Button();
        update.setGraphic(updateview2);
        return update;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setLoggedInUser(String username) {
    }
}