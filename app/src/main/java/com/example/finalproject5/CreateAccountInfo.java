package com.example.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

public class CreateAccountInfo extends AppCompatActivity {

    Button create;

    EditText userInput;
    EditText userPassword;
    EditText FiName;
    EditText LaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_info);

        create = findViewById(R.id.SignUp);
        userInput = findViewById(R.id.CreateUsername);
        userPassword = findViewById(R.id.CreateP);
        FiName = findViewById(R.id.FiName);
        LaName = findViewById(R.id.LaName);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingUser();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void addingUser(){
        if(userInput.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty()||FiName.getText().toString().isEmpty() || LaName.getText().toString().isEmpty()){
            Toast.makeText(CreateAccountInfo.this,"Not all the required fields are completed. Please complete all.",Toast.LENGTH_LONG).show();
        }else{
            String usern = userInput.getText().toString();
            String userp = userPassword.getText().toString();
            String userFN = FiName.getText().toString();
            String userLN = LaName.getText().toString();
            final User user = new User(usern,userp,userFN,userLN);
            UserDao userDao = AppDatabase.getAppDatabase(CreateAccountInfo.this).dao();
            userDao.insert(user);
            goBack();
        }
    }
}
