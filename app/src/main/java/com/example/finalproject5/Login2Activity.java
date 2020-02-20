package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

public class Login2Activity extends AppCompatActivity {

    //EditTexts
    EditText userField;
    EditText passField;

    //Buttons
    Button buttEnter;

    //Table Objects
    UserDao userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        //Connect DB
        userObj = AppDatabase.getAppDatabase(Login2Activity.this).dao();

        //Connect Text Fields
        userField = (EditText) findViewById(R.id.editTextU);
        passField = (EditText) findViewById(R.id.editTextPas);

        //Connect Button
        buttEnter = (Button) findViewById(R.id.buttonEnter);

        //Button Clicked
        buttEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Text from fields
                String tempUser = userField.getText().toString();
                String tempPass = passField.getText().toString();

                //Check DB
                User tempUserObj = userObj.login(tempUser, tempPass);

                //
                if (tempUserObj == null) {
                    //Toast Tell User Course was deleted
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(), "No user found!" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    //Get Username from table object
                    String loginUser = tempUser;

                    //Create Intent for changing screens
                    Intent intentLoginToUserAct = new Intent(Login2Activity.this, UserActivity.class);

                    //Extras
                    intentLoginToUserAct.putExtra("LoggedInUser", loginUser);

                    //Change screens
                    startActivity(intentLoginToUserAct);
                }
            }
        });
    }
}
