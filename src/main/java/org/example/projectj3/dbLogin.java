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

public class dbLogin extends Application {
    public void start(Stage stage){
        Text User = new Text("Username");
        Text Password = new Text("Password");
        TextArea log = new TextArea("Username");
        TextArea pass = new TextArea("Password");


        Button Submit = new Button("Submit");//add new submit button

        User.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Password.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");




        //TaskName.setMaxSize(500, 50);
        //TaskDescription.setMaxSize(500, 150);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(User, log, Password, pass, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);

        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");


        log.setMaxSize(250, 25);
        pass.setMaxSize(250, 25);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);

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

