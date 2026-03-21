package com.tasktracker;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final TaskStorage storage;

    public TaskManager(TaskStorage storage) {
        this.storage = storage;
    }

    public void add(String description) {
        List<Task> tasks = storage.loadTasks();
        int id = storage.getNextId(tasks);

        Task task = new Task(id, description);
        tasks.add(task);

        System.out.println("Tas added successfully (ID: " + id + ")");
    }

    public void update(int id, String newDescription) {
        List<Task> tasks = storage.loadTasks();
        Task task = findById(tasks, id);

        if (task == null) {
            System.err.println("Task not found (ID: " + id + ")");
        }

        task.updateDescription(newDescription);
        storage.saveTasks(tasks);
        System.out.println("Task updated successfully (ID: " + id + ")");
    }

    public void delete(int id) {
        List<Task> tasks = storage.loadTasks();
        Task task = findById(tasks, id);

        if (task == null) {
            System.err.println("Task not found.");
            return;
        }

        tasks.remove(task);
        storage.saveTasks(tasks);
        System.out.println("Task deleted successfully");
    }

    public void markInProgress(int id) {
        List<Task> tasks = storage.loadTasks();
        Task task = findById(tasks, id);

        if (task == null) {
        System.err.println("Task not found.");
        }

        task.setStatus(Task.Status.IN_PROGRESS);
        storage.saveTasks(tasks);
        System.out.println("Task marked as in-progress");
    }
    public void markDone(int id) {
        List<Task> tasks = storage.loadTasks();
        Task task = findById(tasks, id);

        if (task == null) {
        System.err.println("Task not found.");
        }

        task.setStatus(Task.Status.DONE);
        storage.saveTasks(tasks);
        System.out.println("Task marked as done.");
    }
    public void listTasks(String filter) {
        List<Task> tasks = storage.loadTasks();
        List<Task> filtered = filterTasks(tasks, filter);

        if (filtered.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : filtered) {
            System.out.println(formatTask(task));
        }
    }
    public Task findById(List<Task> tasks, int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    
    private String formatTask(Task task) {
        return task.getId() + ". " + task.getDescription() + " [" + task.getStatus() + "]";
    }
    
    private List<Task> filterTasks(List<Task> tasks, String filter) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (filter == null || filter.isEmpty() || task.getStatus().toString().equalsIgnoreCase(filter)) {
                filtered.add(task);
            }
        }
        return filtered;
    }
    
}