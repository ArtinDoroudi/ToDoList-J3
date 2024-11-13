package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUserAuthentication {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            // valid credentials
            User validUser = new User("", "", "", false); // Empty constructor, will set properties if authenticated
            if (validUser.authenticateUser(connection, "john_doe", "password123")) {
                System.out.println("Authentication successful for user: " + validUser.getUserName());
            } else {
                System.out.println("Authentication failed for valid credentials.");
            }

            // invalid credentials
            User invalidUser = new User("", "", "", false);
            if (invalidUser.authenticateUser(connection, "john_doe", "wrongpassword")) {
                System.out.println("Authentication should have failed but succeeded.");
            } else {
                System.out.println("Authentication failed as expected for invalid credentials.");
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
