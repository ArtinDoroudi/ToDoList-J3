package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUserSignUp {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            // Test signing up a new user
            User newUser = new User("new_user", "newuser@example.com", "newpassword123", false);
            if (newUser.signUpUser(connection)) {
                System.out.println("New user registered successfully with username: " + newUser.getUserName());
            } else {
                System.out.println("Sign-up failed for new user.");
            }

            // Test signing up with an existing username
            User existingUser = new User("new_user", "anotheremail@example.com", "password456", false);
            if (existingUser.signUpUser(connection)) {
                System.out.println("Sign-up should have failed but succeeded for duplicate username.");
            } else {
                System.out.println("Sign-up failed as expected for duplicate username.");
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
