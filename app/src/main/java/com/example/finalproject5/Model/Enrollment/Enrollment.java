package com.example.finalproject5.Model.Enrollment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.User.User;

import java.util.Date;

@Entity(tableName = "enrollment_table")
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int enrollmentID;

    private User studentID;
    private Course courseID;
    private Date enrollmentDate;

    public Enrollment(User studentID, Course courseID, Date enrollmentDate) {
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

    public User getStudentID() {
        return studentID;
    }

    public Course getCourseID() {
        return courseID;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
}
