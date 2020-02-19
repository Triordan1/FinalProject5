package com.example.finalproject5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    //Table Objects
    CourseDao courseObj;

    //Create RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recAdapter;

    //Create List for itemModel
    List<ItemModel> listItems;
    List<Course> possibleCourses;

    //Transferred Data from other screen
    String currentUser;
    String sentFirstName;
    String sentLastName;

    //Puts items into appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_uatoolbar, menu);
        return true;
    }

    //Makes menu items clickable
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //Add Button
            case R.id.itemAdd:
                //For now it sends to MainActivity, will send to CreateClassPage in the future
                Intent intentUserToCourse = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intentUserToCourse);
                return true;

            //Edit User Button
            case R.id.itemEdit:
                //Send to Edit User Activity
                Intent intentUserToEdit = new Intent(UserActivity.this, EditUserActivity.class);

                //Get current user from extras
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    currentUser = extras.getString("LoggedInUser");
                }

                //Send user as extra
                intentUserToEdit.putExtra("LoggedInUser", currentUser);

                //Go to edit activity
                startActivity(intentUserToEdit);
                return true;

            //Sign out Button
            case R.id.itemLast:
                //Sign out. Send back to Login Screen
                Intent intentUserToLogin = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intentUserToLogin);
                return true;

            //Default Case
            default:
                //Return Super
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Get Current User Logged in from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("LoggedInUser");
            sentFirstName = extras.getString("LoggedFirstName");
            sentLastName = extras.getString("LoggedLastName");
        }

        //Create String for full name
        String fullname = "Student: " + sentFirstName + " " + sentLastName;

        //Set title to user's first name + last name
        setTitle(fullname);

        //Create Toolbar
        Toolbar uaToolbar = (Toolbar) findViewById(R.id.uaActivity_toolbar);
        setSupportActionBar(uaToolbar);

        //Create table DB instances
        courseObj = AppDatabase.getAppDatabase(UserActivity.this).courseDao();

        //Connect RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set fixed size for items
        recyclerView.setHasFixedSize(true);

        //Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize lists
        listItems = new ArrayList<>();
        possibleCourses = new ArrayList<>();

        //Find users courses and add to itemlist to display
        checkCourses(currentUser);

        //Specify Adapter
        recAdapter = new uaAdapter(listItems, this);

        //Set Adapter to Recycler View
        recyclerView.setAdapter(recAdapter);

    }

    //Function: Checks Course Table for entries with String X
    public void checkCourses(String findUser) {

        //Get all courses
        List<Course> userCourses = courseObj.getAllCoursesWithUser(findUser);

        //Loop through all courses with user name
        for (Course tempCourse: userCourses) {
            //Create a new item list Entry
            ItemModel singleItem = new ItemModel(tempCourse.getTitle(), tempCourse.getInstructor());
            listItems.add(singleItem);
        }
    }
}
