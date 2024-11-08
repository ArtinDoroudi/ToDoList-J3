package org.example.projectj3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDO {
    List<Task> tasks;

    public ToDO() {
        this.tasks = new ArrayList<>();
    }

    // Add a new task to the list
    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    // Delete a task by ID
    public void deleteTask(String taskId) {
        Iterator<Task> iterator = tasks.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId().equals(taskId)) {
                iterator.remove();
                found = true;
                System.out.println("Task deleted: " + task.getDescription());
                break;
            }
        }

        if (!found) {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }

    // Display all tasks in the list
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("- [" + task.getId() + "] " + task.getDescription());
        }
    }

}
