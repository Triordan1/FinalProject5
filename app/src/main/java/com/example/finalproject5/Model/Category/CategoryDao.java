package com.example.finalproject5.Model.Category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the category class. */

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE  + " where username= :username and courseID = :courseID")
    List<Category> getAllCategories(String username, int courseID);
}
