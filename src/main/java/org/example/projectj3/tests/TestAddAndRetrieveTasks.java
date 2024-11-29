package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;
import org.example.projectj3.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestAddAndRetrieveTasks {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();
        try (Connection connection = dbInstance.getConnection()) {
            int userId = 1;

            Task newTask = new Task(0, "Complete Assignment", "Finish Java project for class", false, false);
            if (newTask.saveTask(connection, userId)) {
                System.out.println("New task added and linked to user successfully.");
            } else {
                System.out.println("Failed to add and link task.");
            }

            User user = new User("john_doe", "john@example.com", "password123", false);
            user.setUserId(userId);
            List<Task> tasks = user.getTasks(connection);
            for (Task task : tasks) {
                System.out.println("Task: " + task.getTitle() + ", Description: " + task.getDescription());
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
