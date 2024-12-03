package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.User;

import java.sql.SQLException;

public class TestUser {
    public static void main(String[] args) {
        Database dbInstance = Database.getInstance();
        try {
            User newUser = new User("john_doe", "john@example.com", "password123", false);
            if (newUser.addUser(dbInstance.getConnection())) {
                System.out.println("User added to the database!");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbInstance.closeConnection();
        }
    }
}
