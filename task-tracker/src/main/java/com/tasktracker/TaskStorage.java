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
    }
}