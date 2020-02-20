package com.example.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    public  static  String user = null;

    //Table Objects
    UserDao userObj;
    CourseDao courseObj;
    AssignmentDao assignObj;
    CategoryDao catObj;
    EnrollmentDao enrollObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // check database
        //UserDatabase.getUserDatabase(MainActivity.this).loadData(this);


        ///// Testing /////
        userObj = AppDatabase.getAppDatabase(MainActivity.this).dao();
        courseObj = AppDatabase.getAppDatabase(MainActivity.this).courseDao();
        assignObj = AppDatabase.getAppDatabase(MainActivity.this).assignmentDao();
        catObj = AppDatabase.getAppDatabase(MainActivity.this).categoryDao();
        enrollObj = AppDatabase.getAppDatabase(MainActivity.this).enrollmentDao();

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
        catObj.insert(new Category("Tests",60, "AsDate1", 123,"UserJohn"));
        catObj.insert(new Category("Quizzes",20, "AsDate1", 123,"UserJohn"));
        catObj.insert(new Category("Homework",20, "AsDate1", 123,"UserJohn"));
        assignObj.insert(new Assignment("Test1","details", 50.00, 40.00, "1/1", "12/1", 0, "Tests", "UserJohn"));
        assignObj.insert(new Assignment("Quiz1","details", 50.00, 40.00, "1/1", "12/1", 0, "Quizzes", "UserJohn"));
        assignObj.insert(new Assignment("HW1","details", 50.00, 40.00, "1/1", "12/1", 0, "Homework", "UserJohn"));

        //UserKelly

        AppDatabase.getAppDatabase(MainActivity.this).loadData(this);
        Button create_account_button = findViewById(R.id.create_account);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main","onclick for create account called");
                Intent intent = new Intent(MainActivity.this, CreateAccountInfo.class);
                startActivity(intent);
            }
        });

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main","onclick for login called");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
