package com.example.finalproject5.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.Category;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.Enrollment.Enrollment;
import com.example.finalproject5.Model.Enrollment.EnrollmentDao;
import com.example.finalproject5.Model.Instructor.Instructor;
import com.example.finalproject5.Model.Instructor.InstructorDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import java.util.List;

/** This class is the database used to access and link each of our tables within the database. */

@Database(entities = {User.class, Course.class, Assignment.class, Category.class,
        Enrollment.class, Instructor.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao  dao();

    public static AppDatabase getAppDatabase(final Context context){
        if (instance== null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "db-gradechecker")
                    .allowMainThreadQueries()//temporary for now
                    .build();
        }
        return instance;
    }


    public void loadData(Context context){
        List<User> userList= AppDatabase.getAppDatabase(context).dao().getAllUsers();
        if(userList.size()==0){
            Log.d("AppDatabse","loading users");
            loadusers(context);
        }

    }

    private void loadusers(Context context){
        UserDao dao = getAppDatabase(context).dao();
        User rodrigo= new User("Rodrigo","pass");
        dao().insert(rodrigo);
    }


    public static final String dbName="db-gradechecker";
    public static final String USER_TABLE="user";
    public static final String COURSE_TABLE="course";
    public static final String ASSIGNMENT_TABLE="assignment";
    public static final String CATEGORY_TABLE="category";
    public static final String GRADE_TABLE="Grade";
    public static final String ENROLLMENT_TABLE="enrollment";
    public static final String INSTRUCTOR_TABLE="instructor";

    public abstract CourseDao courseDao();
    public abstract AssignmentDao assignmentDao();
    public abstract CategoryDao categoryDao();
    public abstract EnrollmentDao enrollmentDao();
    public abstract InstructorDao instructorDao();

}
