package com.example.finalproject5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;
import com.example.finalproject5.Model.UserDatabase;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        Log.d("login","oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button loginButton= findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usern= findViewById(R.id.username);
                EditText passw= findViewById(R.id.password);
                String username = usern.getText().toString();
                String password = passw.getText().toString();
                UserDao dao = UserDatabase.getUserDatabase(LoginActivity.this).userDao();
                User user = dao.login(username,password);
                if (user== null){
                    // user not found
                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username or Password is incorrect");
                }else {
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }

            }
        });
    }

}
