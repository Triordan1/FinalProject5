package com.example.finalproject5.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalproject5.MainActivity;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import java.util.List;

@Database(entities = {User.class, Course.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public static final String dbName="db-gradechecker";
    public static final String USER_TABLE="user";
    public static final String COURSE_TABLE="course";

    public abstract UserDao userDao();
    public abstract CourseDao courseDao();

    public static UserDatabase getUserDatabase(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,
                    "db-gradechecker").build();
        }
        return instance;
    }


    public void loadData(Context context) {
        List<User> userList= userDao().getAllUsers();
        if(userList.isEmpty()){
            Log.d("UserDatabase","loading data");
            loadUsers(context);
        }
    }

    private void loadUsers(Context context) {
        UserDao dao = getUserDatabase(context).userDao();

        User Rodrigo = new User("rodrigo","password");
        dao.insert(Rodrigo);
    }
}
