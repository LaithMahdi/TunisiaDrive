package com.example.tunisiadrive;

public class Category {
    private int id;
    private String name;
    private int imageResourceId;

    public Category(int id, String name, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
