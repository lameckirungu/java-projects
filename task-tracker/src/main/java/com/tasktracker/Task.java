package com.tasktracker; // set the namespace so the class lives under com.tasktracker, matching folder path and maven config

import java.time.Instant; // Brings Instant so we can store timestamps
import java.util.Objects;

public class Task {
    public enum Status { // Defines a fixed set of allowed statuses
        TODO, IN_PROGRESS, DONE;

        public static Status fromString(String value) { // adds a safe parser for status strings coming from tasks.json or CLI inputs.
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
    private Instant updatedAt; // changes whenever we modify the task
    
    public Task(int id, String description) {
        this(id, description, Status.TODO, Instant.now(), Instant.now());
    } // convenience constructor for new tasks

    public Task(int id, String description, Status status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status == null ? Status.TODO : status;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        this.updatedAt = updatedAt == null ? Instant.now() : updatedAt;
    } // full constructor used by storage when re-hydrating tasks

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return status;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateDescription(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            return; // prevents blank descriptions from overwriting good data.
        }
        description = newDescription;
        touch(); // refreshes updatedAt whenever data changes
    }
    public void setStatus(Status newStatus) {
        if (newStatus == null) {
            return;
        }
        status = newStatus;
        touch();
    }

    public void touch() {
        updatedAt = Instant.now();
    }

    @Override // signals we're overriding Object methods
    public String toString() {
        return "Task{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}'; // Builds a standard debug format.
    }

    @Override
    public boolean equals(Object other) { // Defines equality as "same task ID"
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task task = (Task) other; // safe cast after type check
        return id == task.id; // Two tasks with the same ID are treated as the saem task
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
