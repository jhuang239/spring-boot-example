package com.ee4216.as2.MovieAPI;

public class TodoList {

    private int id;
    private String owner;
    private String title;
    private String description;
    private String stage;

    public TodoList(int id, String owner, String title, String description, String stage) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.stage = stage;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStage() {
        return stage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

}
