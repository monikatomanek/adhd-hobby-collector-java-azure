package com.adhdhobby.hobbycollector;

public class Hobby {
    private int id;
    private String name;
    private String category;
    private String skillLevel;

    public Hobby(int id, String name, String category, String skillLevel) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.skillLevel = skillLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSkillLevel() {
        return skillLevel;
    }
}