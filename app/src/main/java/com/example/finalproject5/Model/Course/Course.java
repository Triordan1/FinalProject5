package com.example.finalproject5.Model.Course;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

/** This class is the entity used to hold the course details. */

@Entity(tableName = AppDatabase.COURSE_TABLE) // This will work once the database is changed
public class Course {
    @PrimaryKey(autoGenerate = true)//this will change back to normal after implementation of the database
    private int courseID;

    private String instructor;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String username;

    public Course(String instructor, String title, String description, String startDate, String endDate, String username) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}