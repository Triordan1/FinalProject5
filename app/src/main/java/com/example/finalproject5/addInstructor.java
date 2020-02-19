package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addInstructor extends AppCompatActivity {
    //These are the Buttons to add a teacher and to go back if you hit the add instructor by accident
    Button Add;
    Button GB;

    //This is the two edit texts that allow the user to input the teachers name and the title of the course
    EditText firstname;
    EditText lastname;
    EditText courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        //These are the connectors for the buttons and the text edits that allow each of them to have their own unique id
        Add = findViewById(R.id.AIadd);
        GB = findViewById(R.id.AIgoBack);
        firstname = findViewById(R.id.createAFirstN);
        lastname = findViewById(R.id.AITeacher);
        courseId = findViewById(R.id.AICourse);

        //these are the set on click listeners that allow us to call other functions
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
    //this is the add function where it checks if all fields are filled and if they are not filled it will give them a message of error stating that there are missing fields

    public void add(){
        if(lastname.getText().toString().isEmpty() || courseId.getText().toString().isEmpty() || firstname.getText().toString().isEmpty()){
            Toast.makeText(addInstructor.this,"Not all the required fields are completed. Please complete all.", Toast.LENGTH_LONG).show();
        }else{
            String teachlast = lastname.getText().toString();
            String crs = courseId.getText().toString();
            String teachfirst = firstname.getText().toString();
            back();
        }
    }
    //this is the back function which allows the user to go back to the previous page and it is called in both the add and back button
    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
