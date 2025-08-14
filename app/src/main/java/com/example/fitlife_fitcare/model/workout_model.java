package com.example.fitlife_fitcare.model;

public class workout_model {
    private int id;
    private String courseName;
    private String courseDate;

    public workout_model(int id, String courseName, String courseDate) {
        this.id = id;
        this.courseName = courseName;
        this.courseDate = courseDate;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDate() {
        return courseDate;
    }
}