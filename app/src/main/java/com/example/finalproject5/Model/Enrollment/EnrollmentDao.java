package com.example.finalproject5.Model.Enrollment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the enrollment class. */

@Dao
public interface EnrollmentDao {
    @Insert
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollmentr);

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE  + " ORDER BY enrollmentID DESC")
    List<Enrollment> getAllClassEnrollments();
}
