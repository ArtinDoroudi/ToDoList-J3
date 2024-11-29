package org.example.projectj3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.projectj3.Database.DBConst;
import org.example.projectj3.Database.Database;

import java.io.IOException;
import java.sql.*;

import static javafx.application.Application.launch;

public class Adding extends Application {
    public void start(Stage stage){
        Text Task = new Text("Task");
        Text Description = new Text("Description");
        Text Date = new Text("Date");
        Text tag = new Text("Tag");
        TextArea TaskName = new TextArea("Add your new task Here");
        TextArea TaskDescription = new TextArea("Description");
        DatePicker dueDate = new DatePicker();
        Button Dashboard = new Button("Dashboard");
        Button About = new Button("About");
        Button Help = new Button("Help");
        Button Submit = new Button("Submit"); // add new submit button
        ComboBox<String> Tags = new ComboBox<>();
        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        // Event Handlers for the Buttons
        Dashboard.setOnAction(event -> {
            System.out.println("Dashboard button clicked!");
        });
        About.setOnAction(event -> {
            System.out.println("About button clicked!");
        });
        Help.setOnAction(event -> {
            System.out.println("Help button clicked!");
        });
        Submit.setOnAction(event -> {
            // Collect data from form fields
            String taskName = TaskName.getText().trim();
            String taskDescription = TaskDescription.getText().trim();
            String taskDate = dueDate.getValue() != null ? dueDate.getValue().toString() : null;
            String taskTag = Tags.getValue();

            // Validate input
            if (taskName.isEmpty() || taskDescription.isEmpty() || taskDate == null || taskTag == null) {
                System.out.println("Please fill in all fields.");
                return;
            }

            // Insert data into the database
            try {
                int taskId = insertTaskToDatabase(taskName, taskDescription, taskDate);

                if (taskId != -1) {
                    // Optionally link the task to the selected tag
                    boolean tagLinked = linkTaskToTag(taskId, taskTag);
                    if (tagLinked) {
                        System.out.println("Task added successfully with tag!");
                    } else {
                        System.out.println("Task added successfully but failed to link tag.");
                    }
                } else {
                    System.out.println("Failed to add task.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred while adding the task.");
            }
        });



        Task.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Description.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        tag.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");

        Date.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");


        TaskName.setMaxSize(500, 50);
        TaskDescription.setMaxSize(500, 150);



        Dashboard.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        About.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");
        Help.setStyle("-fx-background-color: Transparent; -fx-border-color: Transparent; -fx-font-size: 23; -fx-font-weight: bold;");

        Tags.getItems().addAll("Important", "Gym", "School", "Family");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(Dashboard,About,Help);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(Task,TaskName,Description,TaskDescription,Date, dueDate, tag, Tags, Submit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);

        Submit.setStyle("-fx-background-color: #8cfa8c; -fx-font-size: 15; -fx-font-weight: bold; ");


        //VBox vbox2 = new VBox();
        //vbox2.getChildren().add(Submit);
        //vbox2.setAlignment(Pos.CENTER);
        //vbox2.setSpacing(10);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(hbox);
        //borderPane.setBottom(vbox2);
        borderPane.setStyle("-fx-background-color: TAN");



        Scene scene = new Scene(borderPane, 1000, 500);
        //scene.getStylesheets().add(getClass().getResource("resources/css/styles.css").toExternalForm());
        //String css = this.getClass().getResource("AddModifyStyles.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setTitle("Adding New Task");
        stage.setScene(scene);
        stage.show();
    }private boolean linkTaskToTag(int taskId, String tagTitle) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();

        if (connection == null) {
            System.out.println("Database connection is null.");
            return false;
        }

        String getTagIdSql = "SELECT " + DBConst.TAG_COLUMN_ID + " FROM " + DBConst.TABLE_TAG +
                " WHERE " + DBConst.TAG_COLUMN_TITLE + " = ?";
        String insertTaskTagSql = "INSERT INTO " + DBConst.TABLE_TASK_TAG + " (" +
                DBConst.TASK_TAG_COLUMN_TASK_ID + ", " +
                DBConst.TASK_TAG_COLUMN_TAG_ID + ") VALUES (?, ?)";

        try (PreparedStatement getTagStmt = connection.prepareStatement(getTagIdSql)) {
            getTagStmt.setString(1, tagTitle);
            ResultSet tagResult = getTagStmt.executeQuery();

            if (tagResult.next()) {
                int tagId = tagResult.getInt(DBConst.TAG_COLUMN_ID);

                try (PreparedStatement insertTaskTagStmt = connection.prepareStatement(insertTaskTagSql)) {
                    insertTaskTagStmt.setInt(1, taskId);
                    insertTaskTagStmt.setInt(2, tagId);

                    int rowsInserted = insertTaskTagStmt.executeUpdate();
                    return rowsInserted > 0;
                }
            } else {
                System.out.println("Tag not found: " + tagTitle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private int insertTaskToDatabase(String taskName, String taskDescription, String taskDate) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();

        if (connection == null) {
            System.out.println("Database connection is null.");
            return -1;
        }

        String sql = "INSERT INTO " + DBConst.TABLE_TASK + " (" +
                DBConst.TASK_COLUMN_TITLE + ", " +
                DBConst.TASK_COLUMN_DESCRIPTION + ", " +
                DBConst.TASK_COLUMN_DUE_DATE + ", " +
                DBConst.TASK_COLUMN_IS_COMPLETED + ", " +
                DBConst.TASK_COLUMN_IS_PINNED + ", " +
                "deleted) VALUES (?, ?, ?, FALSE, FALSE, FALSE)";


        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, taskName);
            stmt.setString(2, taskDescription);
            stmt.setString(3, taskDate);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1); // Return Task_ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if insertion fails
    }


    public static void main(String[] args) {
        launch();
    }
}

