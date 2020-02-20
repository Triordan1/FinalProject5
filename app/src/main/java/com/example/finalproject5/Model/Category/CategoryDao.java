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

    @Query("UPDATE Category SET username = :newUsername WHERE username = :givenUsername")
    void updateFromUsername(String newUsername, String givenUsername);

    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE  + " ORDER BY categoryID DESC")
    List<Category> getAllCategories();

    @Query("DELETE FROM " + AppDatabase.CATEGORY_TABLE + " WHERE username = :username AND courseID = :courseID")
    void deleteFromSwipe(String username, String courseID);
}
