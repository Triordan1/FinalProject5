package com.example.finalproject5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import static com.example.finalproject5.MainActivity.user;
//import com.example.finalproject5.Model.UserDatabase;
import com.example.finalproject5.Model.AppDatabase;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("login", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usern = findViewById(R.id.username);
                EditText passw = findViewById(R.id.password);
                String username = usern.getText().toString();
                String password = passw.getText().toString();

                if (user == null) {
                    UserDao dao = AppDatabase.getAppDatabase(LoginActivity.this).dao();
                    User user = dao.login(username, password);
                    if (user == null) {
                        // user not found
                        TextView msg = findViewById(R.id.message);
                        msg.setText("Username or Password is incorrect");
                    } else {
                        MainActivity.user= username;
                        //inform user login was a success
                        Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_LONG).show();

                        //Testing by adding 2 course entries to user "Rodrigo" *Note: This will run everytime you run the login button and there is a user
                       /* Deprecated tests
                        CourseDao cObj = AppDatabase.getAppDatabase(LoginActivity.this).courseDao();
                        cObj.insert(new Course("Rodrigo","CST438","class1","desc1","start1","Rodrigo"));
                        cObj.insert(new Course("Rodrigo","teacher2","class2","desc2","start2","Rodrigo"));
                        */
                        //Get Username from table object
                        String loginUser = user.getUsername();

                        //Create Intent for changing screens
                        Intent intentLoginToUserAct = new Intent(LoginActivity.this, UserActivity.class);

                        //Extras
                        intentLoginToUserAct.putExtra("LoggedInUser", loginUser);

                        //Change screens
                        startActivity(intentLoginToUserAct);
                    }
                }
            }
        });
    }
}
