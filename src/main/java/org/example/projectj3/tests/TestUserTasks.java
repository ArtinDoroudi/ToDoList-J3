package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Task;
import org.example.projectj3.entity.User;

import java.util.List;

public class TestUserTasks {
    public static void main(String[] args) {
        Database dbInstance = Database.getInstance();
        try {
            User user = new User("john_doe", "john@example.com", "password123", false);
            user.setUserId(1);
            List<Task> tasks = user.getTasks(dbInstance.getConnection());
            for (Task task : tasks) {
                System.out.println("Task: " + task.getTitle() + ", Description: " + task.getDescription());
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
