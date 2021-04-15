package com.example.lessondop_first.ui.home.HomeAdapter;

import java.util.Random;

public class HomeModel {
    private int id = new Random().nextInt();

    public HomeModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
