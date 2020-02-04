package com.example.finalproject5.Model.Grade;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.*;

@Entity(tableName = AppDatabase.GRADE_TABLE)
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int gradeID;

    private double earnedScore;
    private double maxScore;
    private int assignmentID;
    private int studentID;
    private int courseID;

    public int getGradeID() {
        return gradeID;
    }

    public double getEarnedScore() {
        return earnedScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public Grade(double earnedScore, double maxScore, int assignmentID, int studentID,
                 int courseID) {
        this.earnedScore = earnedScore;
        this.maxScore = maxScore;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public void setEarnedScore(double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
