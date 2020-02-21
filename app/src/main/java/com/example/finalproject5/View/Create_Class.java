package com.example.finalproject5.View;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.R;
import com.example.finalproject5.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create_Class extends AppCompatActivity {

    /* DONE : hook up class activity buttons to database */
    Button create;
    Button cancel;
    EditText cName;
    EditText cNum;
    EditText cProf;
    EditText sDate;
    EditText eDate;
    CourseDao mCourseDao;
    static String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__class);
        create = findViewById(R.id.classCreate);
        cancel = findViewById(R.id.classCancel);
        cName = findViewById(R.id.className);
        cNum = findViewById(R.id.classSection);
        cProf = findViewById(R.id.classProf);
        sDate = findViewById(R.id.classSDate);
        eDate = findViewById(R.id.ClassEDate);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            currentUser = extras.getString("LoggedInUser");
        }
        mCourseDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .courseDao();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    public void addClass() {
        if (cName.getText().toString().isEmpty() ||cNum.getText().toString().isEmpty() ||cProf.getText().toString().isEmpty() || sDate.getText().toString().isEmpty() ||eDate.getText().toString().isEmpty()) {
            Toast.makeText(Create_Class.this,"Not all the required fields are completed. Please complete all.",Toast.LENGTH_LONG).show();
        }
        else {
            String userN = cName.getText().toString();
            String userNum = cNum.getText().toString();
            String userProf = cProf.getText().toString();
            String userS = sDate.getText().toString();
            String userE = eDate.getText().toString();
            //fixed
            final Course newCourse = new Course(userProf,userN,userNum,userS,userE,currentUser);
            CourseDao cObj = AppDatabase.getAppDatabase(Create_Class.this).courseDao();
            cObj.insert(newCourse);
            goBack();
        }

    }
    public void goBack()
    {
        Intent intent = new Intent(Create_Class.this, UserActivity.class);
        intent.putExtra("LoggedInUser",currentUser);
        startActivity(intent);
    }


}
