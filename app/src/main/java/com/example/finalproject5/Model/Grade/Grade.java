package com.example.finalproject5.Model.Grade;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.AppDatabase;

@Entity(tableName = AppDatabase.GRADE_TABLE)
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int gradeID;

    private double earnedScore;
    private double maxScore;
    private Assignment assignmentID;
    private User studentID;
    private Course courseID;

    public Grade(double earnedScore, double maxScore, Assignment assignmentID, User studentID,
                 Course courseID) {
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

    public void setAssignmentID(Assignment assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setStudentID(User studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }
}
