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
import org.example.projectj3.Database.DBConst;
import org.example.projectj3.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainPage extends Application {
    private VBox taskList;
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Creating all the nodes
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button createTaskButton = new Button("Create New Task");

        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard, About, Help);

        taskList = new VBox();
        taskList.setAlignment(Pos.CENTER);
        taskList.setSpacing(15);

        loadTasks();

        createTaskButton.setOnAction(e -> {
            Stage addingStage = new Stage();
            Adding addingPage = new Adding();
            addingPage.start(addingStage);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(taskList, createTaskButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        // All styles for the nodes
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

    private void loadTasks() {
        taskList.getChildren().clear(); // Clear existing tasks
        try (Connection connection = Database.getInstance().getConnection()) {
            String query = "SELECT * FROM " + DBConst.TABLE_TASK + " WHERE deleted = FALSE";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String taskTitle = rs.getString(DBConst.TASK_COLUMN_TITLE);
                String taskDescription = rs.getString(DBConst.TASK_COLUMN_DESCRIPTION);
                int taskId = rs.getInt(DBConst.TASK_COLUMN_ID); // Get the task ID

                HBox taskRow = addTaskRow(taskTitle, taskDescription, taskId, primaryStage); // Pass taskId
                taskList.getChildren().add(taskRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private HBox addTaskRow(String title, String description, int taskId, Stage primaryStage) {
        CheckBox cb = new CheckBox("Complete");
        Text taskTitleText = new Text(title);

        Image updateicon2 = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview2 = new ImageView(updateicon2);
        Image trashcan2 = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash2 = new ImageView(trashcan2);
        updateview2.setFitWidth(30);
        updateview2.setFitHeight(30);
        trash2.setFitWidth(30);
        trash2.setFitHeight(30);

        Button updateButton = UpdateButton(title, description, taskId, primaryStage); // Corrected
        Button deleteButton = DeleteButton(taskId);

        HBox taskRow = new HBox(cb, taskTitleText, updateButton, deleteButton);
        taskRow.setStyle("-fx-background-color: LIGHTBLUE; -fx-background-radius: 10;");
        taskRow.setAlignment(Pos.CENTER);
        taskRow.setSpacing(15);
        taskRow.setMaxWidth(700);
        taskRow.setMinHeight(50);

        return taskRow;
    }

    private Button DeleteButton(int taskId) {
        Image trashcan2 = new Image(getClass().getResource("/images/bin.png").toExternalForm());
        ImageView trash2 = new ImageView(trashcan2);
        trash2.setFitWidth(30);
        trash2.setFitHeight(30);
        Button delete = new Button();
        delete.setGraphic(trash2);

        // Set action for the delete button
        delete.setOnAction(e -> {
            // Call the method to delete the task from the database
            boolean success = deleteTaskFromDatabase(taskId);
            if (success) {
                // Optionally, refresh the task list or provide feedback
                loadTasks(); // Reload tasks to reflect the deletion
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("Failed to delete task.");
            }
        });

        return delete;
    }

    private Button UpdateButton(String title, String description, int taskId, Stage primaryStage) {
        Image updateicon2 = new Image(getClass().getResource("/images/update4.png").toExternalForm());
        ImageView updateview2 = new ImageView(updateicon2);
        updateview2.setFitWidth(30);
        updateview2.setFitHeight(30);
        Button update = new Button();
        update.setGraphic(updateview2);

        update.setOnAction(event -> {
            // Navigate to the Modify scene
            Modify modify = new Modify(taskId,description,title);
            try {
                modify.start(primaryStage); // Call the start method of Modify to set up the new scene
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        return update;
    }


    private boolean deleteTaskFromDatabase(int taskId) {
        try (Connection connection = Database.getInstance().getConnection()) {
            String query = "UPDATE " + DBConst.TABLE_TASK + " SET deleted = TRUE WHERE " + DBConst.TASK_COLUMN_ID + " = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setLoggedInUser(String username) {
    }
}


