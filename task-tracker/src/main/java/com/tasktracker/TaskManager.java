package com.tasktracker;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final TaskStorage storage;

    public TaskManager(TaskStorage storage) {
        this.storage = storage;
    }

    public void add(String description) {
        List<Task> tasks = storage.loadTasks()
        int id = storage.getNextId(tasks);

        Task task = new Task(id, description);
        tasks.add(task);

        System.out.println("Tas added successfully (ID: " + id + ")");
    }
}