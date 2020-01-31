package com.example.finalproject5.Model.Category;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.Grade.Grade;

import java.util.Date;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String title;
    private int weight;
    private Date assignedDate;
    private Grade gradeID;


    public Category(String title, int weight, Date assignedDate, Grade gradeID) {
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

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Grade getGradeID() {
        return gradeID;
    }
}
