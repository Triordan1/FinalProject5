package com.example.finalproject5.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

}
