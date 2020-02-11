package com.example.finalproject5.Model.Enrollment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.ENROLLMENT_TABLE)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int enrollmentID;

    private int studentID;
    private int courseID;
    private String enrollmentDate;
    private String username;


    public Enrollment(int studentID, int courseID, String enrollmentDate, String username) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
        this.username= username;
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

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
