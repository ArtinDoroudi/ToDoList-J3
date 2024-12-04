package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.projectj3.tables.TagTable;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Application {
    private int loggedInUserId;
    private PieChart chart;
    private String loggedInUser;

    public void setLoggedInUser(String username, int userId) {
        this.loggedInUser = username;
        this.loggedInUserId = userId;
    }
    public void setLoggedInUserId(int userId) {
        this.loggedInUserId = userId;
    }

    @Override
    public void start(Stage stage) {
        chart = new PieChart();
        chart.setTitle("Tag Usage");
        chart.setLabelsVisible(true);
        generateChart();

        Button backButton = new Button("Back to Main Page");
        backButton.setStyle("-fx-background-color: #f5c242; -fx-font-size: 15; -fx-font-weight: bold;");
        backButton.setOnAction(event -> {
            try {
                mainPage mainPageInstance = new mainPage();
                mainPageInstance.setLoggedInUser(loggedInUser);
                mainPageInstance.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

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

        HBox buttonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.CENTER);

        HBox center = new HBox();
        center.getChildren().addAll(vbox, chart);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(15);

        BorderPane layout = new BorderPane();
        layout.setCenter(center);
        layout.setBottom(buttonBox);
        layout.setStyle("-fx-background-color: TAN");

        Scene scene = new Scene(layout, 1000, 500);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void generateChart() {
        try (Connection connection = org.example.projectj3.Database.Database.getInstance().getConnection()) {
            if (connection == null || connection.isClosed()) {
                System.out.println("Database connection is closed or null!");
                return;
            }

            TagTable tagTable = new TagTable(connection);
            List<String> tags = tagTable.getAllTagTitles();
            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

            for (String tag : tags) {
                int count = tagTable.getTagCountByUserId(loggedInUserId, tag);
                if (count > 0) {
                    chartData.add(new PieChart.Data(tag, count));
                }
            }

            chart.setData(chartData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
