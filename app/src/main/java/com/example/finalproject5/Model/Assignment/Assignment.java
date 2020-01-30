package com.example.finalproject5.Model.Assignment;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Course.Course;

import java.util.Date;

@Entity(tableName = "assignment_table")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentID;

    private String details;
    private double maxScore;
    private double earnedScore;
    private Date assignedDate;
    private Date dueDate;
    private Course courseID;
    //will add categoryID


    public Assignment(int assignmentID, String details, double maxScore, double earnedScore,
                      Date assignedDate, Date dueDate, Course courseID) {
        this.assignmentID = assignmentID;
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.courseID = courseID;
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

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Course getCourseID() {
        return courseID;
    }
}



