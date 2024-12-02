package org.example.projectj3.pojo;

public class Tag {
    private int tagId;
    private String title;

    // Constructors
    public Tag(int tagId, String title) {
        this.tagId = tagId;
        this.title = title;
    }

    public Tag(String title) {
        this.title = title;
    }

    public Tag() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", title='" + title + '\'' +
                '}';
    }
}
