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


    public Assignment(int assignmentID, String details, double maxScore, double earnedScore,
                      String assignedDate, String dueDate, int courseID, String categoryID) {
        this.assignmentID = assignmentID;
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.courseID = courseID;
        this.categoryID = categoryID;
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

}



