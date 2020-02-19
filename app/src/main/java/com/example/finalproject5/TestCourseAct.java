package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class TestCourseAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_course);

        String testStr = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            testStr = extras.getString("sentCourse");
        }

        EditText testText;

        testText = (EditText) findViewById(R.id.editText);

        testText.setText(testStr);
    }
}
