
package org.example.projectj3.GUI;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.projectj3.pojo.Tag;
import org.example.projectj3.tables.TagTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.projectj3.Database.Const.*;

public class Datavisualization extends Application {
        private static Datavisualization application;
        private final PieChart chart;

        public Datavisualization(){
            chart = new PieChart();
            chart.setTitle("All Tags Used");
            chart.setLabelsVisible(true);
            generateChart();
        }

        public static Datavisualization getInstance(){
            if(application == null){
                application = new Datavisualization();
            }
            return application;
        }

        public PieChart generateChart() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/iwoodleymd", DB_USER, DB_PASSWORD);
                TagTable table = new TagTable(connection);
                List<Tag> tags = table.getAllTags();

                chart.getData().clear();
                ArrayList<PieChart.Data> data = new ArrayList<>();
                for (Tag tag : tags) {
                    int count = table.getTagCount(tag.getTagId());
                    data.add(new PieChart.Data(tag.getTitle(), count));
                }
                ObservableList<PieChart.Data> ChartData
                        = FXCollections.observableArrayList(data);
                chart.setData(ChartData);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return chart;
        }

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


            Text UpdateUserLogin = new Text("Update User Login");
            Text User = new Text("Username");
            Text Password = new Text("Password");
            TextArea log = new TextArea("Username");
            TextArea pass = new TextArea("Password");
            Button Update = new Button("Update");

            UpdateUserLogin.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            User.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

            Password.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");
            log.setMaxSize(250, 25);
            pass.setMaxSize(250, 25);

            VBox vbox = new VBox();
            vbox.getChildren().addAll(UpdateUserLogin, User,log,Password,pass,Update);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(15);

            BorderPane login = new BorderPane();
            login.setRight(vbox);

            HBox center = new HBox();
            center.getChildren().addAll(vbox, chart);
            center.setAlignment(Pos.CENTER);
            center.setSpacing(15);


            Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(hbox);
            borderPane.setCenter(center);
            borderPane.setStyle("-fx-background-color: TAN");


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

            Scene scene = new Scene(borderPane, 1000, 500);

            stage.setTitle("Update Task");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }



