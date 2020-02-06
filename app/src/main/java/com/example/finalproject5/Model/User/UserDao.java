package com.example.finalproject5.Model.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finalproject5.Model.AppDatabase;

import java.util.List;

/** This class is the data access object used to access the user class. */

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE  + " ORDER BY userID DESC")
    List<User> getAllUsers();

    @Query("select * from " + AppDatabase.USER_TABLE + " where username = :username and password= :password")
    User login(String username, String password);
}
