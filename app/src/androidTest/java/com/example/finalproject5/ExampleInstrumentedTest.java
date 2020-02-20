package com.example.finalproject5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.Category;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.Enrollment.EnrollmentDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    public class TestApp {

        //Table Objects
        AppDatabase db;
        UserDao userObj;
        CourseDao courseObj;
        AssignmentDao assignObj;
        CategoryDao catObj;
        EnrollmentDao enrollObj;

        ///// Testing /////

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
            userObj = db.dao();
            courseObj = db.courseDao();
            assignObj = db.assignmentDao();
            catObj = db.categoryDao();

            ///// Pre-Existing Data /////

            //Add Users
            userObj.insert(new User("UserJohn", "pass", "John", "Smith"));
            userObj.insert(new User("UserKelly", "pass", "Kelly", "Sammy"));
            userObj.insert(new User("UserTom", "pass", "Tom", "Hanks"));
            userObj.insert(new User("UserCharlie", "pass", "Charlie", "Thezron"));
            userObj.insert(new User("Admin", "Admin", "Admin", "Admin"));

            //UserJohn Info
            courseObj.insert(new Course("Mr. Brady","Economics","desc1","start1","end1","UserJohn"));
            courseObj.insert(new Course("Mrs. Obama","Chemistry","desc2","start2","end1","UserJohn"));
            catObj.insert(new Category("Tests", "Economics",60, "AsDate1", 123,"UserJohn"));
            catObj.insert(new Category("Quizzes", "Economics",20, "AsDate1", 123,"UserJohn"));
            catObj.insert(new Category("Homework", "Economics",20, "AsDate1", 123,"UserJohn"));
            catObj.insert(new Category("Tests", "Chemistry",60, "AsDate1", 123,"UserJohn"));
            catObj.insert(new Category("Quizzes", "Chemistry",20, "AsDate1", 123,"UserJohn"));
            catObj.insert(new Category("Homework", "Chemistry",20, "AsDate1", 123,"UserJohn"));
            assignObj.insert(new Assignment("Test1","details", 100.00, 70.00, "1/1", "12/1", 0, "Tests", "UserJohn"));
            assignObj.insert(new Assignment("Quiz1","details", 100.00, 80.00, "1/1", "12/1", 0, "Quizzes", "UserJohn"));
            assignObj.insert(new Assignment("HW1","details", 100.00, 90.00, "1/1", "12/1", 0, "Homework", "UserJohn"));
            assignObj.insert(new Assignment("Test1","details", 100.00, 50.00, "1/1", "12/1", 1, "Tests", "UserJohn"));
            assignObj.insert(new Assignment("Quiz1","details", 100.00, 90.00, "1/1", "12/1", 1, "Quizzes", "UserJohn"));
            assignObj.insert(new Assignment("HW1","details", 100.00, 100.00, "1/1", "12/1", 1, "Homework", "UserJohn"));

            //UserKellu Info
            courseObj.insert(new Course("Mr. Timmy","PE","desc1","start1","end1","UserKelly"));
            courseObj.insert(new Course("Mrs. Little","Art","desc2","start2","end1","UserKelly"));
            catObj.insert(new Category("Tests", "PE",60, "AsDate1", 123,"UserKelly"));
            catObj.insert(new Category("Quizzes", "PE",20, "AsDate1", 123,"UserKelly"));
            catObj.insert(new Category("Homework", "PE",20, "AsDate1", 123,"UserKelly"));
            catObj.insert(new Category("Tests", "Art",60, "AsDate1", 123,"UserKelly"));
            catObj.insert(new Category("Quizzes", "Art",20, "AsDate1", 123,"UserKelly"));
            catObj.insert(new Category("Homework", "Art",20, "AsDate1", 123,"UserKelly"));
            assignObj.insert(new Assignment("Test1","details", 100.00, 70.00, "1/1", "12/1", 3, "Tests", "UserKelly"));
            assignObj.insert(new Assignment("Quiz1","details", 100.00, 80.00, "1/1", "12/1", 3, "Quizzes", "UserKelly"));
            assignObj.insert(new Assignment("HW1","details", 100.00, 90.00, "1/1", "12/1", 3, "Homework", "UserKelly"));
            assignObj.insert(new Assignment("Test1","details", 100.00, 50.00, "1/1", "12/1", 4, "Tests", "UserKelly"));
            assignObj.insert(new Assignment("Quiz1","details", 100.00, 90.00, "1/1", "12/1", 4, "Quizzes", "UserKelly"));
            assignObj.insert(new Assignment("HW1","details", 100.00, 100.00, "1/1", "12/1", 4, "Homework", "UserKelly"));
        }
    }

}
