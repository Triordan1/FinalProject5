package com.example.finalproject5.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject5.Controller.CourseViewAdapter;
import com.example.finalproject5.Create_Assignment;
import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.R;
import com.example.finalproject5.UserActivity;

import java.util.ArrayList;
import java.util.List;

/** This class is used as the holder for our recycler view and the base of our course view page. */

public class CourseView extends AppCompatActivity {

    TextView tvCourseName;
    ImageButton btAdd;
    RecyclerView rvAssignments;
    CourseViewAdapter mAdapter;
    AssignmentDao mAssignmentDao;
    CourseDao mCourseDao;

    String currentUser;
    int courseName;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        currentUser = getIntent().getStringExtra("LoggedInUser");
        courseName = getIntent().getIntExtra("Course", 0);
        mCourseDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .courseDao();

        Course course = mCourseDao.getCourseFromID(courseName);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        mAssignmentDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();
        List<Assignment> mAssignments = mAssignmentDao.getAllCourseAssignments(currentUser,courseName);

        tvCourseName = findViewById(R.id.courseName);
        rvAssignments = findViewById(R.id.rvAssignments);
        btAdd = findViewById(R.id.btAdd);
        tvCourseName.setText(course.getTitle());

        mAdapter = new CourseViewAdapter(getApplicationContext(),mAssignments,currentUser);

        rvAssignments.setAdapter(mAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String options[] = {"Edit Categories", "Add Assignment"};

                AlertDialog.Builder builder = new AlertDialog.Builder(CourseView.this);
                builder.setTitle("What would you like to do?");
                builder.setCancelable(true);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0) {
                            //create intent to go to edit categories
                        } else {
                            //create intent to go to add assignment
                            Intent intent = new Intent(CourseView.this, Create_Assignment.class);
                            intent.putExtra("LoggedInUser",currentUser);
                            intent.putExtra("Course",courseName);
                            startActivity(intent);
                        }
                    }
                });

                builder.show();
            }
        });
    }
    public void goBack()
    {
        Intent intent = new Intent(CourseView.this, UserActivity.class);
        intent.putExtra("LoggedInUser",currentUser);
        startActivity(intent);
    }

}
