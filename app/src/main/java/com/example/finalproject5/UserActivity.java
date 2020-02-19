package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
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
    static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Create table DB instances
        courseObj = AppDatabase.getAppDatabase(UserActivity.this).courseDao();

        //Connect RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set fixed size for items
        recyclerView.setHasFixedSize(true);

        //Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize list
        listItems = new ArrayList<>();
        possibleCourses = new ArrayList<>();

        //Get Current User Logged in from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("LoggedInUser");
        }

        //Find users courses and add to itemlist to display
        checkCourses(currentUser);

        //Specify Adapter
        recAdapter = new uaAdapter(listItems, this);

        //Set Adapter to Recycler View
        recyclerView.setAdapter(recAdapter);

    }

    //Check Course Table for entries with String X
    public void checkCourses(String findUser) {

        //Get all courses
        List<Course> userCourses = courseObj.getAllCoursesWithUser(findUser);
        Grade gradeGet = new Grade();

        //Loop through all courses with user name
        for (Course tempCourse: userCourses) {
            double grade= gradeGet.getGrade(UserActivity.this,findUser,tempCourse.getCourseID());
            //Create a new item list Entry
            ItemModel singleItem = new ItemModel(tempCourse.getCourseID(), tempCourse.getTitle(), tempCourse.getInstructor(),grade);
            listItems.add(singleItem);
        }
    }
}
