
package org.example.projectj3.GUI;

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
                ObservableList<PieChart.Data> chartData
                        = FXCollections.observableArrayList(data);
                chart.setData(chartData);
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






            //  ObservableList<PieChart.Data> pieChart =
            //          FXCollections.observableArrayList(
            //                 new PieChart.Data("Family", 13),
            //                  new PieChart.Data("School", 25),
            //                  new PieChart.Data("Gym", 10),
            //                  new PieChart.Data("Important", 22));
            //  final PieChart chart = new PieChart(pieChart);
            // chart.setLabelLineLength(10);
            // chart.setLegendSide(Side.LEFT);
            // chart.setTitle("Tags Used");

            //"Important", "Gym", "School", "Family"


            Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(hbox);
            borderPane.setCenter(chart);
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



