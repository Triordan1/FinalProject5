package com.example.finalproject5.Model.Enrollment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

/** This class is the entity used to hold the enrollment details. */

@Entity(tableName = AppDatabase.ENROLLMENT_TABLE)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int enrollmentID;

    private int studentID;
    private int courseID;
    private String enrollmentDate;

    public Enrollment(int studentID, int courseID, String enrollmentDate) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }
}
