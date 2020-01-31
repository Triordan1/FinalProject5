package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountInfo extends AppCompatActivity {

    Button create;

    EditText userInput;
    EditText userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_info);

        create = findViewById(R.id.SignUp);
        userInput = findViewById(R.id.CreateUsername);
        userPassword = findViewById(R.id.CreateP);

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
        if(userInput.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty()){
            Toast.makeText(CreateAccountInfo.this,"Not all the required fields are completed. Please complete all.",Toast.LENGTH_LONG).show();
        }else{
            String usern = userInput.getText().toString();
            String userp = userPassword.getText().toString();
            goBack();
        }
    }
}
