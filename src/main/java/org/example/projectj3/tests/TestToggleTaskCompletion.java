package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;

import java.sql.Connection;
import java.sql.SQLException;

public class TestToggleTaskCompletion {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            Task task = new Task(1, "Complete Assignment", "Finish Java project", false, false);

            if (task.toggleCompletionStatus(connection)) {
                System.out.println("Task completion status toggled successfully.");
            } else {
                System.out.println("Failed to toggle task completion status.");
            }

            System.out.println("Task Completed Status: " + task.isCompleted());
        } finally {
            dbInstance.closeConnection();
        }
    }
}
