package org.example.projectj3.tables;

import org.example.projectj3.DAO.TagDAO;
import org.example.projectj3.pojo.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagTable implements TagDAO {
    private final Connection connection;

    public TagTable(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String query = "SELECT * FROM tags_table";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(
                        resultSet.getInt("Tag_ID"),
                        resultSet.getString("Tag_Title")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    @Override
    public Tag getTagById(int tagId) {
        String query = "SELECT * FROM tags_table WHERE Tag_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tagId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Tag(
                        resultSet.getInt("Tag_ID"),
                        resultSet.getString("Tag_Title")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createTag(Tag tag) {
        String query = "INSERT INTO tags_table (Tag_Title) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tag.getTitle());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    tag.setTagId(keys.getInt(1)); // Set the generated ID
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTag(Tag tag) {
        String query = "UPDATE tags_table SET Tag_Title = ? WHERE Tag_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tag.getTitle());
            statement.setInt(2, tag.getTagId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTag(int tagId) {
        String query = "DELETE FROM tags_table WHERE Tag_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tagId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean linkTagToTask(int tagId, int taskId) {
        String query = "INSERT INTO task_tag_table (Tag_ID, Task_ID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tagId);
            statement.setInt(2, taskId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tag> getTagsByTaskId(int taskId) {
        List<Tag> tags = new ArrayList<>();
        String query = "SELECT t.* FROM tags_table t " +
                "JOIN task_tag_table tt ON t.Tag_ID = tt.Tag_ID " +
                "WHERE tt.Task_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(
                        resultSet.getInt("Tag_ID"),
                        resultSet.getString("Tag_Title")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
    public int getTagCount(int tag) {
        String query = "SELECT COUNT(*) AS TagCount " +
                "FROM task_tag_table tt " +
                "JOIN task_table t ON tt.Task_ID = t.Task_ID " +
                "WHERE t.User_ID = ? AND tt.Tag_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tag);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TagCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
