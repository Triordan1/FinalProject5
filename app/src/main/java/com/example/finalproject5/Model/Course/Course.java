package com.example.finalproject5.Model.Course;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.COURSE_TABLE) // This will work once the database is changed
public class Course {
    @PrimaryKey(autoGenerate = true)//this will change back to normal after implementation of the database
    private int courseID;

    private String instructor;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

    public Course(String instructor, String title, String description, Date startDate, Date endDate) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}