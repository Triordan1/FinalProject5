package com.example.finalproject5.Model.Grade;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

@Dao
public interface GradeDao {
    @Insert
    void insert(Grade grade);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE  + " ORDER BY gradeID DESC")
    List<Grade> getAllGrades();
}
