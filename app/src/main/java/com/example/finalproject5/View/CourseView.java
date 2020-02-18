package com.example.finalproject5.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject5.Controller.CourseViewAdapter;
import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.R;

import java.util.ArrayList;
import java.util.List;

/** This class is used as the holder for our recycler view and the base of our course view page. */

public class CourseView extends AppCompatActivity {

    TextView tvCourseName;
    RecyclerView rvAssignments;
    CourseViewAdapter mAdapter;
    AssignmentDao mAssignmentDao;
    CourseDao mCourseDao;

    String currentUser;
    String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        currentUser = getIntent().getStringExtra("User");
        courseName = String.valueOf(getIntent().getIntExtra("Course", 0));

        mCourseDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .courseDao();
       Course course = mCourseDao.getCourseFromID(courseName);

        mAssignmentDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();
        List<Assignment> mAssignments = mAssignmentDao.getAllAssignments();

        tvCourseName = findViewById(R.id.courseName);
        rvAssignments = findViewById(R.id.rvAssignments);
        tvCourseName.setText(course.getTitle());

        mAdapter = new CourseViewAdapter(mAssignments);

        rvAssignments.setAdapter(mAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getAssignments(String user, String course) {

    }
}
