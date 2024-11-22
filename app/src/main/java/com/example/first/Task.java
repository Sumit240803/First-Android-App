package com.example.first;

public class Task {
    private int id;        // Task ID (primary key from the database)
    private String task;   // Task description
    private int status;    // Task status (e.g., 0 for incomplete, 1 for complete)

    // Constructor
    public Task(int id, String task, int status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

