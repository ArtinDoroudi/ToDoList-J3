package org.example.projectj3.Database;

import java.net.URL;
import java.sql.*;
import org.example.projectj3.Database.Const.*;

import static org.example.projectj3.Database.Const.*;
import static org.example.projectj3.Database.DBConst.*;

public class Database {
    private static Database instance;
    private static Connection connection = null;

    public Database() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3307/" + DB_NAME
                                        // + "?useSSL=false",
                                        + "?serverTimezone=UTC",
                                DB_USER,
                                DB_PASSWORD);
                System.out.println("Connected to database!");
                createTable("users", CREATE_TABLE_USER, connection);
                createTable("tasks", CREATE_TABLE_TASK, connection);
                createTable("tags", CREATE_TABLE_TAG, connection);
                createTable("user_tasks", CREATE_TABLE_USER_TASK, connection);
                createTable("task_tags", CREATE_TABLE_TASK_TAG, connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Reconnect logic if connection is closed
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + DB_NAME + "?serverTimezone=UTC",
                    DB_USER,
                    DB_PASSWORD);
        }
        return connection;
    }


    public void closeConnection() {
        System.out.println("Closing connection...");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables(DB_NAME, null, tableName, null);
        if (resultSet.next()) {
            System.out.println(tableName + " table already exists");
        } else {
            createTable = connection.createStatement();
            createTable.executeUpdate(tableQuery);
            System.out.println(tableName + " table created");
        }
    }

    // Method to add a new task
    public boolean addTask(String taskName, String description, String dueDate) {
        String sql = "INSERT INTO " + TABLE_TASK + " (" + TASK_COLUMN_TITLE + ", " +
                TASK_COLUMN_DESCRIPTION + ", " + TASK_COLUMN_DUE_DATE +
                ") VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taskName);
            statement.setString(2, description);
            statement.setString(3, dueDate);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task added successfully!");
                return true;
            } else {
                System.out.println("Failed to add task.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}