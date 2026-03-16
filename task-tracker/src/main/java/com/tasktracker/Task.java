package com.tasktracker; // set the namespace so the class lives under com.tasktracker, matching folder path and maven config

import java.time.Instant; // Brings Instant so we can store timestamps
import java.util.Objects;

public class Task {
    public enum Status { // Defines a fixed set of allowed statuses
        TODO, IN_PROGRESS, DONE;

        public static Status fromString(String value) {
            if (value == null) {
                return TODO;
            }
            String normalized = value.trim().toLowerCase();

            if (normalized.equals("todo")) {
                return TODO;
            }
            if (normalized.equals("in-progress") || normalized.equals("in_progress")) {
                return IN_PROGRESS;
            }
            if (normalized.equals("done")) {
                return DONE;
            }
            return TODO;
        }

        public String toStorageString() {
            if (this == IN_PROGRESS) {
                return "in-progress";
            }
            return name().toLowerCase();
        }
    }

    private final int id; // Id never changes once assigned
    private String description;
    private Status status;
    private final Instant createdAt;
    private final Instant updatedAt;
    
    public Task(int id, String description) {
        this(id, description, Status.TODO, Instant.now(), Instant.now());
    }

    public Task(int id, String description, Status status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status == null ? Status.TODO : status;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        this.updatedAt = updatedAt == null ? Instant.now() : updatedAt;
    }

    public int getId() {
        return id;
    }
    public int getDescription() {
        return description;
    }
    public int getStatus() {
        return status;
    }
    public int getCreatedAt() {
        return createdAt;
    }
    public int getUpdatedAt() {
        return updatedAt;
    }
    public void setStatus(Status newStatus) {
        if (newStatus == null) {
            return;
        }
        status == newStatus;
        touch();
    }

}
