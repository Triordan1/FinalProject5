package com.example.finalproject5.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

@Database(entities = {User.class, Course.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    public static final String dbName="db-gradechecker";
    public static final String USER_TABLE="user";
    public static final String COURSE_TABLE="course";

    public abstract UserDao userDao();
    public abstract CourseDao courseDao();

}
