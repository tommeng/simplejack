package com.tommeng.simplejackexample.model;

public class MyColor implements BaseModel {
    private long id;
    private String title;
    private String hex;
    private String description;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHex() {
        return hex;
    }

    public String getDescription() {
        return description;
    }
}
