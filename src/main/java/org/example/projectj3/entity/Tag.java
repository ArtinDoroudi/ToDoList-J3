package org.example.projectj3.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag {
    private int tagId;
    private String title;

    // Constructor
    public Tag(String title) {
        this.title = title;
    }

    // Getters and Setters
    public int getTagId() { return tagId; }
    public String getTitle() { return title; }
    public void setTagId(int tagId) { this.tagId = tagId; }
    public void setTitle(String title) { this.title = title; }

    // add a new tag to the tags_table
    public boolean addTag(Connection connection) {
        String query = "INSERT INTO tags_table (Tag_Title) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, this.title);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    this.tagId = keys.getInt(1); // Set the tagId with the generated ID
                }
                System.out.println("Tag added successfully.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // link a tag to a task
    public boolean linkTagToTask(Connection connection, int taskId) {
        String query = "INSERT INTO task_tag_table (Tag_ID, Task_ID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.tagId);
            statement.setInt(2, taskId);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Tag linked to task successfully.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
