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
        Button Submit = new Button("Submit");

        // Step 2: Create Event Handlers for the Buttons
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




        Task.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");

        Description.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");


        TaskName.setMaxSize(500, 50);
        TaskDescription.setMaxSize(500, 200);



        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(Task,TaskName,Description,TaskDescription, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

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
