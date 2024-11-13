package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;
import org.example.projectj3.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestDeleteTask {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            Task task = new Task(1, "Sample Title", "Sample Description", false, false);

            if (task.deleteTask(connection)) {
                System.out.println("Task marked as deleted successfully.");
            } else {
                System.out.println("Failed to mark task as deleted.");
            }

            User user = new User("john_doe", "john@example.com", "password123", false);
            user.setUserId(1);
            List<Task> tasks = user.getTasks(connection);

            System.out.println("User's active tasks:");
            for (Task activeTask : tasks) {
                System.out.println("Task: " + activeTask.getTitle() + ", Description: " + activeTask.getDescription());
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
