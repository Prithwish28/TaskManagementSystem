package com.mapping;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskManagerApp {

    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add a Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update a Task");
            System.out.println("4. Delete a Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Task
                    Task task = new Task();
                    System.out.print("Enter title: ");
                    task.setTitle(scanner.nextLine());

                    System.out.print("Enter description: ");
                    task.setDescription(scanner.nextLine());

                    System.out.print("Enter priority (High/Medium/Low): ");
                    task.setPriority(scanner.nextLine());

                    System.out.print("Enter deadline (yyyy-MM-dd): ");
                    try {
                        Date deadline = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                        task.setDeadline(deadline);
                    } catch (Exception e) {
                        System.out.println("Invalid date format!");
                        continue;
                    }

                    taskDAO.createTask(task);
                    System.out.println("Task added successfully!");
                    break;

                case 2: // View All Tasks
                    System.out.println("\nAll Tasks:");
                    taskDAO.getAllTasks().forEach(t -> System.out.println(t.getId() + ". " + t.getTitle() + " (" + t.getPriority() + ")"));
                    break;

                case 3: // Update Task
                    System.out.print("Enter task ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Task updateTask = new Task();
                    updateTask.setId(updateId);

                    System.out.print("Enter new title: ");
                    updateTask.setTitle(scanner.nextLine());

                    System.out.print("Enter new description: ");
                    updateTask.setDescription(scanner.nextLine());

                    System.out.print("Enter new priority: ");
                    updateTask.setPriority(scanner.nextLine());

                    taskDAO.updateTask(updateTask);
                    System.out.println("Task updated successfully!");
                    break;

                case 4: // Delete Task
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    taskDAO.deleteTask(deleteId);
                    System.out.println("Task deleted successfully!");
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    taskDAO.closeFactory();
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
