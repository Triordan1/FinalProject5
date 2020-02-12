package com.example.finalproject5;

import android.os.Bundle;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

            //For now removed

            /*
            final Course newCourse = new Course(userProf,userN,userNum,userS,userE);
            mCourseDao.insert(newCourse);

             */
        }

    }


}
