package com.example.finalproject5.Model.Enrollment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnrollmentDao {
    @Insert
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollmentr);

    @Query("SELECT * FROM enrollment_table ORDER BY enrollmentID DESC")
    List<Enrollment> getAllClassEnrollments();
}
