package org.example.projectj3.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private boolean isPremium;

    // Constructor
    public User(String userName, String email, String password, boolean isPremium) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isPremium = isPremium;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isPremium() { return isPremium; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPremium(boolean isPremium) { this.isPremium = isPremium; }

    // Method to add a new user to the database
    public boolean addUser(Connection connection) {
        String query = "INSERT INTO user_table (User_Name, Email, Password, is_Premium) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, this.userName);
            statement.setString(2, this.email);
            statement.setString(3, this.password);
            statement.setBoolean(4, this.isPremium);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added successfully.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signUpUser(Connection connection) {
        // Check if username already exists
        String checkQuery = "SELECT * FROM user_table WHERE User_Name = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, this.userName);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                System.out.println("Username already exists. Please choose a different username.");
                return false;
            }

            // Username is unique, so proceed to add the user
            return addUser(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean authenticateUser(Connection connection, String userName, String password) {
        String query = "SELECT * FROM user_table WHERE User_Name = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // User authenticated; set user properties based on the database entry
                this.userId = ((ResultSet) resultSet).getInt("User_ID");
                this.userName = resultSet.getString("User_Name");
                this.email = resultSet.getString("Email");
                this.isPremium = resultSet.getBoolean("is_Premium");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Task> getTasks(Connection connection) {
        return Task.getTasksByUserId(this.userId, connection);
    }
}
