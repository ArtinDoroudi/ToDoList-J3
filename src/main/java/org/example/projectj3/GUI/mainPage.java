package org.example.projectj3.GUI;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.projectj3.Database.Database;
import org.example.projectj3.tables.TaskTable;
import org.example.projectj3.pojo.Task;

import java.sql.Connection;
import java.util.List;

public class mainPage extends Application {
    private VBox taskList;
    private String loggedInUser; // Store logged-in username
    private int loggedInUserId;  // Store logged-in user ID

    public void setLoggedInUser(String username) {
        this.loggedInUser = username;
        try (Connection connection = Database.getInstance().getConnection()) {
            if (connection != null) {
                this.loggedInUserId = new org.example.projectj3.tables.UserTable(connection).getUserIdByUsername(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Button dashboardButton = createNavigationButton("Dashboard");
        Button aboutButton = createNavigationButton("About");
        Button helpButton = createNavigationButton("Help");

        HBox navBar = new HBox(dashboardButton, aboutButton, helpButton);
        navBar.setAlignment(Pos.CENTER);
        navBar.setSpacing(10);

        taskList = new VBox();
        taskList.setAlignment(Pos.CENTER);
        taskList.setSpacing(15);

        Button createTaskButton = new Button("Create New Task");
        createTaskButton.setStyle("-fx-background-color: #8cfa8c; -fx-font-weight: bold;");
        createTaskButton.setOnAction(e -> openAddingPage());

        VBox taskSection = new VBox(taskList, createTaskButton);
        taskSection.setAlignment(Pos.CENTER);
        taskSection.setSpacing(15);

        loadTasksForUser();

        BorderPane layout = new BorderPane();
        layout.setTop(navBar);
        layout.setCenter(taskSection);
        layout.setStyle("-fx-background-color: TAN;");

        Scene scene = new Scene(layout, 1000, 500);
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNavigationButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        addHoverEffect(button);
        return button;
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        button.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
    }

    private void openAddingPage() {
        Stage addingStage = new Stage();
        Adding addingPage = new Adding();
        addingPage.setLoggedInUserId(loggedInUserId); // Pass user ID to the Adding page
        addingPage.start(addingStage);
    }

    private void loadTasksForUser() {
        taskList.getChildren().clear();
        try (Connection connection = Database.getInstance().getConnection()) {
            if (connection != null) {
                TaskTable taskTable = new TaskTable(connection);
                List<Task> tasks = taskTable.getTasksByUserId(loggedInUserId); // Get tasks for the logged-in user
                for (Task task : tasks) {
                    taskList.getChildren().add(createTaskRow(task));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HBox createTaskRow(Task task) {
        CheckBox completeCheckBox = new CheckBox();
        completeCheckBox.setSelected(task.isCompleted());
        Text taskTitle = new Text(task.getTitle());

        Button updateButton = createUpdateButton(task);
        Button deleteButton = createDeleteButton(task);

        HBox taskRow = new HBox(completeCheckBox, taskTitle, updateButton, deleteButton);
        taskRow.setAlignment(Pos.CENTER);
        taskRow.setSpacing(15);
        taskRow.setStyle("-fx-background-color: LIGHTBLUE; -fx-background-radius: 10;");
        return taskRow;
    }

    private Button createUpdateButton(Task task) {
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #f5c242; -fx-font-weight: bold;");
        updateButton.setOnAction(e -> {
            // Navigate to Modify page
            Modify modifyPage = new Modify(task.getTaskId());
            Stage modifyStage = new Stage();
            try {
                modifyPage.start(modifyStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return updateButton;
    }

    private Button createDeleteButton(Task task) {
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #fa8c8c; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> {
            try (Connection connection = Database.getInstance().getConnection()) {
                if (connection != null) {
                    TaskTable taskTable = new TaskTable(connection);
                    boolean isDeleted = taskTable.deleteTask(task.getTaskId());
                    if (isDeleted) {
                        System.out.println("Task deleted successfully.");
                        loadTasksForUser(); // Reload the task list
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return deleteButton;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
