package org.example.projectj3.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
