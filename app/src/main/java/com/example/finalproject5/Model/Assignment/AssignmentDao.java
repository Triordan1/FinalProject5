package com.example.finalproject5.Model.Assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the assignment class. */

@Dao
public interface AssignmentDao {
    @Insert
    void insert(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE  + " ORDER BY assignmentID DESC")
    List<Assignment> getAllAssignments();


    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE username = :user and courseID = :courseID")
    List<Assignment> getAllCourseAssignments(String user, int courseID);


    @Query("select * from " + AppDatabase.ASSIGNMENT_TABLE + " where username = :username and categoryID = :categoryID and courseID = :courseID ")
    List<Assignment> getAll(String username, String categoryID, int courseID);



    @Query("UPDATE Assignment SET username = :newUsername WHERE username = :givenUsername")
    void updateFromUsername(String newUsername, String givenUsername);


    @Query("DELETE FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE username = :username AND courseID = :courseID")
    void deleteFromSwipe(String username, int courseID);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE assignmentID = :id")
    Assignment getAssignmentByID(int id);



}


