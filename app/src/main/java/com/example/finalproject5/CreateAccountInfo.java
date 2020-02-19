package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.finalproject5.Model.AppDatabase;

import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

public class CreateAccountInfo extends AppCompatActivity {

    Button create;
    Button back;

    EditText username;
    EditText firstName;
    EditText lastName;
    EditText userPassword;

    UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_info);

        create = findViewById(R.id.SignUp);
        username = findViewById(R.id.CREATEUsername);
        firstName = findViewById(R.id.CreateFirstName);
        lastName = findViewById(R.id.CreateLastName);
        userPassword = findViewById(R.id.CreateP);
        back = findViewById(R.id.CrGB);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingUser();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void addingUser(){
        if(username.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){
            Toast.makeText(CreateAccountInfo.this,"Not all the required fields are completed. Please complete all.",Toast.LENGTH_LONG).show();
        }else{
            String usern = username.getText().toString();
            String userp = userPassword.getText().toString();

            String fname = firstName.getText().toString();
            String lname= lastName.getText().toString();
            final User newUser = new User(usern,userp,fname,lname);
            mUserDao.insert(newUser);

//            UserDao userDao = AppDatabase.getAppDatabase(CreateAccountInfo.this).dao();
            mUserDao.insert(newUser);
            goBack();
        }
    }
}
