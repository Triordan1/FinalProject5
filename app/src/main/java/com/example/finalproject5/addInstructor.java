package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addInstructor extends AppCompatActivity {
    Button Add;
    Button GB;

    EditText teacher;
    EditText course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        Add = findViewById(R.id.AIadd);
        GB = findViewById(R.id.AIgoBack);
        teacher = findViewById(R.id.AITeacher);
        course = findViewById(R.id.AICourse);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        GB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }
    public void add(){
        if(teacher.getText().toString().isEmpty() || course.getText().toString().isEmpty()){
            Toast.makeText(addInstructor.this,"Not all the required fields are completed. Please complete all.", Toast.LENGTH_LONG).show();
        }else{
            String teach = teacher.getText().toString();
            String crs = course.getText().toString();
            back();
        }
    }
    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
