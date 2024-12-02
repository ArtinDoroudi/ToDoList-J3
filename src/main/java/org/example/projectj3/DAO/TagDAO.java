package org.example.projectj3.DAO;

import org.example.projectj3.pojo.Tag;

import java.util.List;

public interface TagDAO {
    // Fetch all tags
    List<Tag> getAllTags();

    // Fetch a tag by its ID
    Tag getTagById(int tagId);

    // Create a new tag
    boolean createTag(Tag tag);

    // Update an existing tag
    boolean updateTag(Tag tag);

    // Delete a tag by its ID
    boolean deleteTag(int tagId);

    // Link a tag to a task
    boolean linkTagToTask(int tagId, int taskId);

    // Fetch all tags linked to a specific task
    List<Tag> getTagsByTaskId(int taskId);
}
