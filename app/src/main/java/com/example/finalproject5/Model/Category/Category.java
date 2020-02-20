package com.example.finalproject5.Model.Category;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.AppDatabase;

/** This class is the entity used to hold the category details. */

@Entity(tableName = AppDatabase.CATEGORY_TABLE)
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String title;
    private String courseID;
    private int weight;
    private String assignedDate;
    private int gradeID;
    private String username;


    //Constructor
    public Category(String title, String courseID, int weight, String assignedDate, int gradeID, String username) {
        this.title = title;
        this.courseID = courseID;
        this.weight = weight;
        this.assignedDate = assignedDate;
        this.gradeID = gradeID;
        this.username = username;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getTitle() {
        return title;
    }

    public int getWeight() {
        return weight;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
