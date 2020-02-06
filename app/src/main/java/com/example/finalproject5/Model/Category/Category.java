package com.example.finalproject5.Model.Category;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Grade.Grade;
import com.example.finalproject5.Model.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.CATEGORY_TABLE)
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String title;
    private int weight;
    private String assignedDate;
    private int gradeID;


    public Category(String title, int weight, String assignedDate, int gradeID) {
        this.title = title;
        this.weight = weight;
        this.assignedDate = assignedDate;
        this.gradeID = gradeID;
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
}
