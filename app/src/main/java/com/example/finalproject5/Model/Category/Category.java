package com.example.finalproject5.Model.Category;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String title;
    private int weight;
    private Date assignedDate;
    //will add grade ID


    public Category(String title, int weight, Date assignedDate) {
        this.title = title;
        this.weight = weight;
        this.assignedDate = assignedDate;
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
}
