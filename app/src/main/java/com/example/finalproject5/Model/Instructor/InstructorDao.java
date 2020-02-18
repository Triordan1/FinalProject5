package com.example.finalproject5.Model.Instructor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the instructor class. */

@Dao
public interface InstructorDao {
    @Insert
    void insert(Instructor instructor);

    @Update
    void update(Instructor instructor);

    @Delete
    void delete(Instructor instructor);

    @Query("SELECT * FROM " + AppDatabase.INSTRUCTOR_TABLE  + " ORDER BY instructorID DESC")
    List<Instructor> getAllInstructors();
}