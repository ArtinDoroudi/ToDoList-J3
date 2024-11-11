package org.example.projectj3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToDO toDoList = new ToDO();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a task");
            System.out.println("2. Delete a task");
            System.out.println("3. Display all tasks");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    toDoList.addTask(description);
                    break;
                case "2":
                    System.out.print("Enter task ID to delete: ");
                    String taskId = scanner.nextLine();
                    toDoList.deleteTask(taskId);
                    break;
                case "3":
                    toDoList.displayTasks();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }
}


