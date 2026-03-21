package com.tasktracker;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String STORAGE_FILE = "tasks.json";

    public List<Task> loadTasks() {
        Path path = Paths.get(STORAGE_FILE);
        ensureFileExists(path);

        String raw;
        try {
            raw = Files.readString(path).trim();
        } catch (IOException e) {
            System.err.println("Failed to read tasks.json: " + e.getMessage());
            return new ArrayList<>();
        }
        if (raw.isEmpty() || raw.equals("[]")) {
            return new ArrayList<>();
        }
        return parseTasks(raw);
    }

    public void saveTasks(List<Task> tasks) {
        Path path = Paths.get(STORAGE_FILE);
        ensureFileExists(path);

        String json = toJson(tasks);
        try {
            Files.writeString(path, json);
        } catch (IOException e) {
            System.err.println("Failed to write tasks.json: " + e.getMessage());
        }
    }
    public int getNextId(List<Task> tasks) {
        int max = 0;
        for (Task task : tasks) {
            if (task.getId() > max) {
                max = task.getId();
            }
        }
        return max + 1;
    }

    private void ensureFileExists(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
        } catch (IOException e) {
            System.err.println("Failed to create tasks.json: " + e.getMessage());
        }
    }

    private List<Task> parseTasks(String json) {
        // TODO: Implement JSON parsing logic
        return new ArrayList<>();
    }

    private String toJson(List<Task> tasks) {
        // TODO: Implement JSON serialization logic
        return "[]";
    }
}