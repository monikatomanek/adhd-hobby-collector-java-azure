package com.adhdhobby.hobbycollector;

public class DeletedProject {
    private int deletedId;
    private int projectId;
    private int hobbyId;
    private String projectName;
    private String status;
    private String notes;
    private String startedDate;
    private String deletedDate;

    public DeletedProject(int deletedId, int projectId, int hobbyId, String projectName, String status, String notes, String startedDate, String deletedDate) {
        this.deletedId = deletedId;
        this.projectId = projectId;
        this.hobbyId = hobbyId;
        this.projectName = projectName;
        this.status = status;
        this.notes = notes;
        this.startedDate = startedDate;
        this.deletedDate = deletedDate;
    }

    public int getDeletedId() {
        return deletedId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getHobbyId() {
        return hobbyId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public String getDeletedDate() {
        return deletedDate;
    }
}