package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalproject5.Model.User.UserDao;

public class EditUserActivity extends AppCompatActivity {

    //Table Objects
    UserDao userObj;

    //Extras data
    String sentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Get Extras first
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sentUser = extras.getString("LoggedInUser");
        }



    }
}
