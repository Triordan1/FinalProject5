package com.example.finalproject5.Model.Assignment;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Category.Category;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE)
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentID;

    private String details;
    private double maxScore;
    private double earnedScore;
    private String assignedDate;
    private String dueDate;
    private int courseID;
    private String categoryID;
    private String username;


    public Assignment(int assignmentID, String details, double maxScore, double earnedScore,
                      String assignedDate, String dueDate, int courseID, String categoryID, String username) {
        this.assignmentID = assignmentID;
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.courseID = courseID;
        this.categoryID = categoryID;
        this.username= username;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public String getDetails() {
        return details;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public double getEarnedScore() {
        return earnedScore;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public void setEarnedScore(double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

}



