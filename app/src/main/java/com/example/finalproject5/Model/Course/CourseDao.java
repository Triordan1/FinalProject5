package com.example.finalproject5.Model.Course;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the course class. */

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE  + " ORDER BY courseID DESC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE title = :title")
    Course getCourseFromTitle(String title);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE username = :user")
    List<Course> getAllCoursesWithUser(String user);
}