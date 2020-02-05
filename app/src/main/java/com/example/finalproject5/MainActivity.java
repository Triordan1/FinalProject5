package com.example.finalproject5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.finalproject5.Model.*;

public class MainActivity extends AppCompatActivity {
    public  static  String user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // check database
        AppDatabase.getAppDatabase(MainActivity.this).loadData(this);
        Button create_account_button = findViewById(R.id.create_account);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main","onclick for create account called");
                Intent intent = new Intent(MainActivity.this, CreateAccountInfo.class);
                startActivity(intent);
            }
        });

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main","onclick for login called");
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
