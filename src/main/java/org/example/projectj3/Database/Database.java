package org.example.projectj3.Database;

import java.sql.*;

import static org.example.projectj3.Database.DBConst.*;

public class Database {
    private static Database instance;
    private Connection connection;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public boolean initializeConnection(String dbName, String dbUser, String dbPassword) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        return reconnect();
    }

    private boolean reconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/" + dbName + "?serverTimezone=UTC",
                    dbUser,
                    dbPassword
            );
            System.out.println("Connected to database!");

            createTable("users", CREATE_TABLE_USER);
            createTable("tasks", CREATE_TABLE_TASK);
            createTable("tags", CREATE_TABLE_TAG);
            createTable("user_tasks", CREATE_TABLE_USER_TASK);
            createTable("task_tags", CREATE_TABLE_TASK_TAG);

            return true;
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return false;
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            reconnect();
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTable(String tableName, String tableQuery) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
            if (tables.next()) {
                System.out.println(tableName + " table already exists");
            } else {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(tableQuery);
                    System.out.println("Created table: " + tableName);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during table creation for " + tableName + ": " + e.getMessage());
        }
    }
}
