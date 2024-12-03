package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.projectj3.Database.Database;
import org.example.projectj3.pojo.Task;
import org.example.projectj3.tables.TagTable;
import org.example.projectj3.tables.TaskTable;

import java.sql.Connection;
import java.util.List;

public class Adding extends Application {
    private int loggedInUserId = 1; // This should be set dynamically when navigating to this page
    private Connection connection;

    public void setLoggedInUserId(int userId) {
        this.loggedInUserId = userId;
    }

    @Override
    public void start(Stage stage) {
        connection = Database.getInstance().getConnection();
        if (connection == null) {
            System.out.println("Failed to connect to the database. Exiting...");
            return;
        }

        Text taskLabel = new Text("Task");
        Text descriptionLabel = new Text("Description");
        Text dateLabel = new Text("Date");
        Text tagLabel = new Text("Tag");

        TextArea taskNameField = new TextArea();
        taskNameField.setPromptText("Add your new task here");
        taskNameField.setMaxSize(500, 50);

        TextArea taskDescriptionField = new TextArea();
        taskDescriptionField.setPromptText("Description");
        taskDescriptionField.setMaxSize(500, 150);

        DatePicker dueDatePicker = new DatePicker();

        ComboBox<String> tagsComboBox = new ComboBox<>();

        // Populate tag dropdown
        TagTable tagTable = new TagTable(connection);
        List<String> tags = tagTable.getAllTagTitles();
        if (!tags.isEmpty()) {
            tagsComboBox.getItems().addAll(tags);
        } else {
            System.out.println("No tags found in the database.");
        }

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold;");

        submitButton.setOnAction(event -> {
            String taskName = taskNameField.getText().trim();
            String taskDescription = taskDescriptionField.getText().trim();
            String taskDate = dueDatePicker.getValue() != null ? dueDatePicker.getValue().toString() : null;
            String taskTag = tagsComboBox.getValue();

            if (taskName.isEmpty() || taskDescription.isEmpty() || taskDate == null || taskTag == null) {
                System.out.println("Please fill in all fields.");
                return;
            }

            Task task = new Task(taskName, taskDescription, false, false);
            task.setDueDate(taskDate);

            try {
                TaskTable taskTable = new TaskTable(connection);

                // Create the task and associate it with the logged-in user
                boolean isTaskCreated = taskTable.createTask(task, loggedInUserId);

                if (isTaskCreated) {
                    System.out.println("Task created with ID: " + task.getTaskId());

                    // Link the task to the selected tag
                    int tagId = tagTable.getTagIdByTitle(taskTag);
                    if (tagId > 0) {
                        boolean tagLinked = tagTable.linkTagToTask(task.getTaskId(), tagId);
                        if (tagLinked) {
                            System.out.println("Task added successfully with tag!");
                        } else {
                            System.out.println("Task added successfully, but failed to link tag.");
                        }
                    } else {
                        System.out.println("Tag not found in the database: " + taskTag);
                    }
                } else {
                    System.out.println("Failed to add task.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred while adding the task.");
            }
        });

        VBox formBox = new VBox();
        formBox.getChildren().addAll(taskLabel, taskNameField, descriptionLabel, taskDescriptionField, dateLabel, dueDatePicker, tagLabel, tagsComboBox, submitButton);
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);

        BorderPane layout = new BorderPane();
        layout.setCenter(formBox);
        layout.setStyle("-fx-background-color: tan");

        Scene scene = new Scene(layout, 1000, 500);
        stage.setTitle("Adding New Task");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}