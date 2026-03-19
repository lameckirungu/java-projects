package com.tasktracker;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

public class TaskStorage {
    private static final String STORAGE_FILE = "tasks.json";

    public List<Task> loadTasks() {
        Path path = Paths.get(STORAGE_FILE);
        ensureFileExists(path);
    }
}