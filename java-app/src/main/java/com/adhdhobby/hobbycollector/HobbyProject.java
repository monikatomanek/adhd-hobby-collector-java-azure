package com.adhdhobby.hobbycollector;

public class HobbyProject {
    private int id;
    private int hobbyId;
    private String projectName;
    private String status;
    private String notes;
    private String startedDate;
    private Integer motivationLevel;

    public HobbyProject(int id, int hobbyId, String projectName, String status, String notes, String startedDate, Integer motivationLevel) {
        this.id = id;
        this.hobbyId = hobbyId;
        this.projectName = projectName;
        this.status = status;
        this.notes = notes;
        this.startedDate = startedDate;
        this.motivationLevel = motivationLevel;
    }

    public int getId() {
        return id;
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

    public Integer getMotivationLevel() {
        return motivationLevel;
    }
}