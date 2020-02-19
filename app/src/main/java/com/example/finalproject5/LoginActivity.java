package com.example.finalproject5;

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
                //UserDao dao = UserDatabase.getUserDatabase(LoginActivity.this).userDao();
                //User user = dao.login(username,password);
                if (user == null) {
                    UserDao dao = AppDatabase.getAppDatabase(LoginActivity.this).dao();
                    User user = dao.login(username, password);
                    if (user == null) {
                        // user not found
                        TextView msg = findViewById(R.id.message);
                        msg.setText("Username or Password is incorrect");
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //This code is very well maintained, I liked the indentation

                        //// Johnnys Portion, You can edit/delete it all //////

                        //Testing by adding 2 course entries to user "rod" *Note: This will run everytime you run the login button and there is a user
                        CourseDao cObj = AppDatabase.getAppDatabase(LoginActivity.this).courseDao();
                        cObj.insert(new Course("rod","teacher1","class1","desc1","start1","end1"));
                        cObj.insert(new Course("rod","teacher2","class2","desc2","start2","end2"));

                        //Get Username from table object
                        String loginUser = user.getUsername();
                        String loginFirstName = user.getFirstName();
                        String loginLastName = user.getLastName();

                        //Create Intent for changing screens
                        Intent intentLoginToUserAct = new Intent(LoginActivity.this, UserActivity.class);

                        //Extras
                        intentLoginToUserAct.putExtra("LoggedInUser", loginUser);
                        intentLoginToUserAct.putExtra("LoggedFirstName", loginFirstName);
                        intentLoginToUserAct.putExtra("LoggedLastName", loginLastName);

                        //Change screens
                        startActivity(intentLoginToUserAct);

                        ///// End of Johnnys Portion /////
                    }
                }
            }
        });
    }
}
