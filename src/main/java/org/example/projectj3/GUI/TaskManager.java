package org.example.projectj3.GUI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.example.projectj3.entity.Task;

import java.sql.Connection;
import java.util.List;

public class TaskManager {

    private VBox taskContainer;  // VBox where tasks will be displayed
    private Connection connection;  // Connection to the database
    private int userId;  // User ID to fetch tasks for

    // Constructor
    public TaskManager(VBox taskContainer, Connection connection, int userId) {
        this.taskContainer = taskContainer;
        this.connection = connection;
        this.userId = userId;
    }

    // Refresh task list on the main page
    public void refreshTaskList() {
        taskContainer.getChildren().clear();  // Clear existing tasks

        // Fetch tasks from the database
        List<Task> tasks = Task.getTasksByUserId(userId, connection);

        // Create and add task rows dynamically
        for (Task task : tasks) {
            HBox taskRow = createTaskRow(task);  // Create a row for each task
            taskContainer.getChildren().add(taskRow);  // Add task row to the container
        }
    }

    // Create a task row (UI component) for each task
    private HBox createTaskRow(Task task) {
        CheckBox cb = new CheckBox("Complete");
        Text taskText = new Text(task.getTitle());
        Button update = new Button();
        Button delete = new Button();

        // Set up the update and delete icons/buttons
        update.setGraphic(new ImageView(new Image(getClass().getResource("/images/update4.png").toExternalForm())));
        delete.setGraphic(new ImageView(new Image(getClass().getResource("/images/bin.png").toExternalForm())));

        // Create a row for the task and add components
        HBox taskRow = new HBox();
        taskRow.setAlignment(Pos.CENTER);
        taskRow.getChildren().addAll(cb, taskText, update, delete);
        taskRow.setStyle("-fx-background-color: LIGHTBLUE; -fx-background-radius: 10;");
        taskRow.setSpacing(20);
        taskRow.setMaxWidth(500);
        taskRow.setMinHeight(50);

        // Set actions for buttons (optional)
        update.setOnAction(event -> updateTask(task));
        delete.setOnAction(event -> deleteTask(task));

        return taskRow;
    }

    // Handle task update (optional functionality)
    private void updateTask(Task task) {
        // Implement the update task logic, such as opening an edit form
        System.out.println("Updating task: " + task.getTitle());
    }

    // Handle task deletion
    private void deleteTask(Task task) {
        // Delete task from the database
        if (task.deleteTask(connection)) {
            System.out.println("Task deleted: " + task.getTitle());
            refreshTaskList();  // Refresh task list after deletion
        }
    }
}
