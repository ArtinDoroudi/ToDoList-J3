package org.example.projectj3.pojo;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isPinned;
    private String dueDate;

    // Constructor
    public Task(int taskId, String title, String description, boolean isCompleted, boolean isPinned) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isPinned = isPinned;
    }

    // Constructor without taskId (for creating new tasks)
    public Task(String title, String description, boolean isCompleted, boolean isPinned) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isPinned = isPinned;
    }

    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", isPinned=" + isPinned +
                '}';
    }

}