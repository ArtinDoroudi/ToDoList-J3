package org.example.projectj3.GUI;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import org.example.projectj3.Database.DBConst;
import org.example.projectj3.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javafx.application.Application.launch;

public class Modify extends Application {
    private int taskId;
    private TextField titleField;
    private TextArea descriptionField; // To identify the task in the database

    public Modify(int taskId, String title, String description) {
        this.taskId = taskId;
        this.titleField = new TextField(title);
        this.descriptionField = new TextArea(description);
    }

    public void start(Stage stage){
        Text UpdateTask = new Text("Update Task");
        Text UpdateDescription = new Text("Update Description");
        Text Date = new Text("Date");
        Text tag = new Text("Tag");
        TextArea UpdateTaskName = new TextArea("Update your task Here");
        TextArea UpdateTaskDescription = new TextArea(" Update Description");
        DatePicker dueDate = new DatePicker();
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit");
        ComboBox Tags = new ComboBox();

        Image image = new Image(getClass().getResource("/images/update.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(200);
        imageView.setFitHeight(80);

        UpdateTask.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        UpdateDescription.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        tag.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Date.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");


        UpdateTaskName.setMaxSize(500, 50);
        UpdateTaskDescription.setMaxSize(500, 150);
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






        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 20; -fx-font-weight: bold;");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        HBox imagepane = new HBox();
        imagepane.getChildren().add(imageView);
        // imagepane.setPadding(new Insets(10));



        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(UpdateTask,UpdateTaskName,UpdateDescription,UpdateTaskDescription,Date, dueDate, tag, Tags, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(3);

        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");

        BorderPane Top = new BorderPane();
        Top.setRight(imagepane);
        Top.setLeft(hbox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(Top);
        borderPane.setStyle("-fx-background-color: TAN");



        Scene scene = new Scene(borderPane, 1000, 500);

        stage.setTitle("Update Task");
        stage.setScene(scene);
        stage.show();

        Submit.setOnAction(event -> {
            String updatedTaskName = UpdateTaskName.getText().trim();
            String updatedDescription = UpdateTaskDescription.getText().trim();
            String updatedDueDate = dueDate.getValue() != null ? dueDate.getValue().toString() : null;
            String updatedTag = Tags.getValue() != null ? Tags.getValue().toString() : null; // Assuming you're getting the selected tag from the ComboBox

            if (!updatedTaskName.isEmpty() && !updatedDescription.isEmpty()) {
                // Make sure taskId is available in this scope, e.g. if it's a class field, otherwise declare it
                if (updateTaskInDatabase(taskId, updatedTaskName, updatedDescription, updatedTag, updatedDueDate)) {
                    System.out.println("Task updated successfully!");
                    stage.close(); // Close the Modify page after successful update
                } else {
                    System.out.println("Failed to update the task.");
                }
            } else {
                System.out.println("Task name and description cannot be empty!");
            }
        });








    }

    public static void main(String[] args) {
        launch();
    }
    private boolean updateTaskInDatabase(int taskId, String taskName, String description, String tag, String dueDate) {
        String updateQuery = "UPDATE " + DBConst.TABLE_TASK + " SET " +
                DBConst.TASK_COLUMN_TITLE + " = ?, " +
                DBConst.TASK_COLUMN_DESCRIPTION + " = ?, " +
                DBConst.TASK_COLUMN_DUE_DATE + " = ? " +
                "WHERE " + DBConst.TASK_COLUMN_ID + " = ?";


        PreparedStatement preparedStatement = null;
        try (Connection connection = Database.getConnection()) {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, dueDate);
            preparedStatement.setInt(4, taskId);

            // Log the query for debugging
            System.out.println("Executing query: " + preparedStatement.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }}