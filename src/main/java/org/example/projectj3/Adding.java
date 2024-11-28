package org.example.projectj3;

import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Adding extends Application {
    public void start(Stage stage){
        Text Task = new Text("Task");
        Text Description = new Text("Description");
        Text Date = new Text("Date");
        Text tag = new Text("Tag");
        TextArea TaskName = new TextArea("Add your new task Here");
        TextArea TaskDescription = new TextArea("Description");
        DatePicker dueDate = new DatePicker();
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit"); // add new submit button
        ComboBox<String> Tags = new ComboBox<>();
        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        // Event Handlers for the Buttons
        Dashboard.setOnAction(event -> {
            System.out.println("Dashboard button clicked!");
        });
        About.setOnAction(event -> {
            System.out.println("About button clicked!");
        });
        Help.setOnAction(event -> {
            System.out.println("Help button clicked!");
        });
        Submit.setOnAction(event -> {
            System.out.println("Submit button clicked!");
        });

        // add the button transitions to the navigation bar
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


        Task.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Description.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        tag.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Date.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");


        TaskName.setMaxSize(500, 50);
        TaskDescription.setMaxSize(500, 150);



        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");

        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(Task,TaskName,Description,TaskDescription,Date, dueDate, tag, Tags, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);

        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");


        //VBox vbox2 = new VBox();
        //vbox2.getChildren().add(Submit);
        //vbox2.setAlignment(Pos.CENTER);
        //vbox2.setSpacing(10);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        //borderPane.setBottom(vbox2);
        borderPane.setStyle("-fx-background-color: TAN");



        Scene scene = new Scene(borderPane, 1000, 500);
        //scene.getStylesheets().add(getClass().getResource("resources/css/styles.css").toExternalForm());
        //String css = this.getClass().getResource("AddModifyStyles.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setTitle("Adding New Task");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

