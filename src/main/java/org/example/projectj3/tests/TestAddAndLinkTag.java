package org.example.projectj3.tests;

import org.example.projectj3.Database.Database;
import org.example.projectj3.entity.Tag;

import java.sql.Connection;
import java.sql.SQLException;

public class TestAddAndLinkTag {
    public static void main(String[] args) throws SQLException {
        Database dbInstance = Database.getInstance();

        try (Connection connection = dbInstance.getConnection()) {
            // Add a new tag
            Tag newTag = new Tag("Important");
            if (newTag.addTag(connection)) {
                System.out.println("New tag added successfully with ID: " + newTag.getTagId());

                // Link this tag to an existing task with ID 1
                if (newTag.linkTagToTask(connection, 1)) {
                    System.out.println("Tag linked to task successfully.");
                } else {
                    System.out.println("Failed to link tag to task.");
                }
            } else {
                System.out.println("Failed to add tag.");
            }
        } finally {
            dbInstance.closeConnection();
        }
    }
}
