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

    @Query("UPDATE User SET username = :newUsername WHERE username = :givenUsername")
    void updateFromUsername(String newUsername, String givenUsername);

    @Query("UPDATE User SET password = :newPassword WHERE username = :newUsername")
    void updatePassFromUser(String newPassword, String newUsername);

    @Query("UPDATE User SET firstName = :newFN WHERE username = :newUsername")
    void updateFNFromUser(String newFN, String newUsername);

    @Query("UPDATE User SET lastName = :newLN WHERE username = :newUsername")
    void updateLNFromUser(String newLN, String newUsername);

    @Query("select * from " + AppDatabase.USER_TABLE + " where username = :username")
    User getUserFromName(String username);
}
