package com.example.finalproject5.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject5.Controller.CourseViewAdapter;
import com.example.finalproject5.R;

public class CourseView extends AppCompatActivity {

    TextView tvCourseName;
    RecyclerView rvAssignments;
    CourseViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);

        tvCourseName = findViewById(R.id.courseName);
        rvAssignments = findViewById(R.id.rvAssignments);

        rvAssignments.setAdapter(mAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
