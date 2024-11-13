package org.example.projectj3.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isPinned;

    // Constructor
    public Task(int taskId, String title, String description, boolean isCompleted, boolean isPinned) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isPinned = isPinned;
    }

    // Getters (no need for setters right now)
    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
    public boolean isPinned() { return isPinned; }

    // Static method to get tasks by user ID
    public static List<Task> getTasksByUserId(int userId, Connection connection) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task_table t " +
                "JOIN user_task_table ut ON t.Task_ID = ut.Task_ID " +
                "WHERE ut.User_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("Task_ID"),
                        resultSet.getString("Task_Title"),
                        resultSet.getString("Task_Description"),
                        resultSet.getBoolean("is_Completed"),
                        resultSet.getBoolean("is_Pinned")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public boolean saveTask(Connection connection, int userId) {
        String query = "INSERT INTO task_table (Task_Title, Task_Description, is_Completed, is_Pinned) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, this.title);
            statement.setString(2, this.description);
            statement.setBoolean(3, this.isCompleted);
            statement.setBoolean(4, this.isPinned);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    this.taskId = keys.getInt(1);

                    // so here we link the new task with the user in user_task_table
                    String linkQuery = "INSERT INTO user_task_table (User_ID, Task_ID) VALUES (?, ?)";
                    try (PreparedStatement linkStmt = connection.prepareStatement(linkQuery)) {
                        linkStmt.setInt(1, userId);
                        linkStmt.setInt(2, this.taskId);
                        linkStmt.executeUpdate();
                    }
                    System.out.println("Task added and linked to user successfully.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
