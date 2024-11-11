package org.example.projectj3;

import java.util.List;

public class User {
    private String username;
    private String password;
    private boolean isPremium;
    private List<String> tasks;

    public User(String username, String password, boolean isPremium) {
        this.username = username;
        this.password = password;
        this.isPremium = isPremium;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String task) {
        // Add a new task
    }

    public void deleteTask(String task) {
        // Delete an existing task
    }

    public void modifyTask(String oldTask, String newTask) {
        // Modify an existing task(we should pass an object instead of two strings but for now we will keep it simple)
    }

    public boolean canAddMoreTasks() {
        // Check if the user can add more tasks (based on premium status)
        return false;
    }

    public void logout() {
        // Logout the user
    }
}
