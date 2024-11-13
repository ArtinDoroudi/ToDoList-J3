package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdateTask {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            Task task = new Task(1, "Original Title", "Original Description", false, false);

            if (task.updateTask(connection, "Updated Title", "Updated Description", true)) {
                System.out.println("Task updated successfully with new title: " + task.getTitle());
            } else {
                System.out.println("Failed to update task.");
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
