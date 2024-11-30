package org.example.projectj3;

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
import org.example.projectj3.entity.Tag;

import javax.swing.text.html.HTML;
import java.util.ArrayList;

public class DataVisualization extends Application {
    /*private static DataVisualization application;
    private final PieChart chart;
    private DataVisualization(){
        chart = new PieChart();
        chart.setTitle("All Tags Used");
        chart.setLabelsVisible(true);
        generateChart();
    }
    public static DataVisualization getInstance(){
        if(application == null){
            application = new DataVisualization();
        }
        return application;
    }
      public PieChart generateChart() {
           TagTable table = new TagTable();
           ArrayList<Tag> tags = table.getAllTags();
           chart.getData().clear();
           ArrayList<PieChart.Data> pie = new ArrayList<>();
           for (Tag tag : tags) {
               pie.add(new PieChart.Data(tag.getName()));
           }
               ObservableList<PieChart.Data> data =
                       FXCollections.observableArrayList();
               chart.setData(data);
               return chart;
           }*/

    public void start(Stage stage){
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit");




        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 21; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 21; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 21; -fx-font-weight: bold;");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);


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
        //borderPane.setCenter(chart);
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