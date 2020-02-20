package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.View.CourseView;

public class Create_Assignment extends AppCompatActivity {
    EditText AssName;
    EditText AssDetails;
    EditText AssMax;
    EditText AssEarned;
    EditText AssDate;
    EditText AssDue;
    EditText AssType;
    Button AssCreate;
    Button AssCancel;
    static String currentUser;
    static int currentCourseID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__assignment);
        AssName = findViewById(R.id.AssName);
        AssDetails = findViewById(R.id.AssDetails);
        AssMax = findViewById(R.id.AssMax);
        AssEarned = findViewById(R.id.AssEarned);
        AssDate = findViewById(R.id.AssDate);
        AssDue = findViewById(R.id.AssDue);
        AssCreate = findViewById(R.id.AssCreate);
        AssCancel = findViewById(R.id.AssCancel);
        AssType = findViewById(R.id.AssType);
        currentUser = getIntent().getStringExtra("LoggedInUser");
        AssCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAssignment();
            }
        });
        AssCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        currentCourseID = getIntent().getIntExtra("courseID",1);
    }
    public void createAssignment()
    {
        if(AssName.getText().toString().isEmpty()||AssDetails.getText().toString().isEmpty()||AssMax.getText().toString().isEmpty()||AssEarned.getText()
                .toString().isEmpty()||AssDate.getText().toString().isEmpty()||AssDue.getText().toString().isEmpty()|| AssType.getText().toString().isEmpty()) {
            Toast.makeText(Create_Assignment.this,"Not all the required fields are completed. Please complete all.",Toast.LENGTH_LONG).show();
        }
        else if(validInput(AssType.getText().toString()))
        {
            Toast.makeText(Create_Assignment.this,"The only valid assignment types are: Quiz,Test,HW, and Final",Toast.LENGTH_LONG).show();
        }
        else {
            String uName = AssName.getText().toString();
            String uDetails = AssDetails.getText().toString();
            double uMax = Double.parseDouble(AssMax.getText().toString());
            double uEarned = Double.parseDouble(AssEarned.getText().toString());
            String uDate = AssDate.getText().toString();
            String uDue = AssDue.getText().toString();


            final Assignment assignment = new Assignment(uName, uDetails, uMax, uEarned, uDate, uDue, currentCourseID, uDetails, currentUser);
            AssignmentDao cObj = AppDatabase.getAppDatabase(Create_Assignment.this).assignmentDao();
            cObj.insert(assignment);
            goBack();
        }

    }
    public boolean validInput(String input)
    {
        if(input.equals("Quiz")||input.equals("Test")||input.equals("HW")||input.equals("Final"))
        {
            return true;
        }
        return false;
    }
    public void goBack()
    {
        Intent intent = new Intent(Create_Assignment.this, CourseView.class);
        intent.putExtra("LoggedInUser",currentUser);
        startActivity(intent);
    }
}
