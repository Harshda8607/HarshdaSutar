package com.example.fitlife_fitcare.model;


public class workout_model {
    private String courseName;
    private String date;

    public workout_model(String courseName, String date) {
        this.courseName = courseName;
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDate() {
        return date;
    }
}

