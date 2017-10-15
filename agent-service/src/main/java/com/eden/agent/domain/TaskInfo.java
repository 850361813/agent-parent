package com.eden.agent.domain;

public class TaskInfo {
    private long id;
    private String taskName;
    private int taskStatus;
    private String taskDisp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDisp() {
        return taskDisp;
    }

    public void setTaskDisp(String taskDisp) {
        this.taskDisp = taskDisp;
    }
}
