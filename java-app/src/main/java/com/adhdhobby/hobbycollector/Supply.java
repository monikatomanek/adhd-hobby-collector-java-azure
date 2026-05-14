package com.adhdhobby.hobbycollector;

public class Supply {
    private int id;
    private int hobbyId;
    private String itemName;
    private double cost;
    private boolean essential;
    private String supplyType;

    public Supply(int id, int hobbyId, String itemName, double cost, boolean essential, String supplyType) {
        this.id = id;
        this.hobbyId = hobbyId;
        this.itemName = itemName;
        this.cost = cost;
        this.essential = essential;
        this.supplyType = supplyType;
    }

    public int getId() {
        return id;
    }

    public int getHobbyId() {
        return hobbyId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }

    public boolean isEssential() {
        return essential;
    }

    public String getSupplyType() {
        return supplyType;
    }
}