package org.example.projectj3.Database;
import java.sql.*;

import org.example.projectj3.Database.Const.*;

import static org.example.projectj3.Database.Const.*;
import static org.example.projectj3.Database.DBConst.*;

public class Database {
    private static Database instance;
    private Connection connection = null;
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

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        System.out.println("Closing connection...");
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            connection = null;
            e.printStackTrace();
        }
    }

    public void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables("adoroudimd", null, tableName, null);
        if (resultSet.next()) {
            System.out.println(tableName + " table already exists");
        } else {
            createTable = connection.createStatement();
            createTable.executeUpdate(tableQuery);
            System.out.println(tableName + " table created");
        }
    }
}