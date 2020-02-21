package com.example.finalproject5.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.Enrollment.EnrollmentDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;
import com.example.finalproject5.R;
import com.example.finalproject5.UserActivity;

/**
 * The EditUserActivity class will edit user info such as username and first name
 * and will allow the user to change the values.  The user will see a form with the
 * pre-existing user values inputted into the text fields.  The user can then change
 * the values and update the database but only after pressing the update button.
 * @author Johnny Huynh
 */

public class EditUserActivity extends AppCompatActivity {

    //Table Objects
    UserDao userObj;
    CourseDao courseObj;
    AssignmentDao assignObj;
    CategoryDao catObj;
    EnrollmentDao enrollObj;

    //Extras data
    String sentUser;

    //EditTexts
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText userNameEdit;
    EditText passwordEdit;

    //Button
    Button buttUpdate;

    //Create new variables for updating
    String newFirstname;
    String newLastname;
    String newUsername;
    String newPassword;

    //Create temp variables for holding old data for updating
    String oldFirstname;
    String oldLastname;
    String oldUsername;
    String oldPassword;

    //Puts items into appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eptoolbar, menu);
        return true;
    }

    //Menu Items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Back Button
            case R.id.itemBack:
                Intent intentEditToUser = new Intent(EditUserActivity.this, UserActivity.class);

                //Get Username first
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    sentUser = extras.getString("LoggedInUser");
                }

                //Send extra
                intentEditToUser.putExtra("LoggedInUser", sentUser);

                startActivity(intentEditToUser);
                return true;

            //Sign out Button
            case R.id.itemLast:
                //Sign out. Send back to Login Screen
                Intent intentEditToLogin = new Intent(EditUserActivity.this, LoginActivity.class);
                startActivity(intentEditToLogin);
                return true;

            //Default Case
            default:
                //Return Super
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Get Extras first
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sentUser = extras.getString("LoggedInUser");
        }

        //Create Title
        String title = ("User: " + sentUser);

        //Set Title
        setTitle(title);

        //Create Toolbar
        Toolbar epToolbar = (Toolbar) findViewById(R.id.epActivity_toolbar);
        setSupportActionBar(epToolbar);

        //Connect EditTexts
        firstNameEdit = (EditText) findViewById(R.id.editTextFirstname);
        lastNameEdit = (EditText) findViewById(R.id.editTextLastname);
        userNameEdit = (EditText) findViewById(R.id.editTextUsername);
        passwordEdit = (EditText) findViewById(R.id.editTextPassword);

        //Connect Button
        buttUpdate = (Button) findViewById(R.id.buttonUpdate);

        //Get Database
        userObj = AppDatabase.getAppDatabase(EditUserActivity.this).dao();
        courseObj = AppDatabase.getAppDatabase(EditUserActivity.this).courseDao();
        assignObj = AppDatabase.getAppDatabase(EditUserActivity.this).assignmentDao();
        catObj = AppDatabase.getAppDatabase(EditUserActivity.this).categoryDao();
        enrollObj = AppDatabase.getAppDatabase(EditUserActivity.this).enrollmentDao();

        //Get the table of the user
        final User userEditTarget = userObj.getUserFromName(sentUser);

        //Set Textfields with existing data
        firstNameEdit.setText(userEditTarget.getFirstName());
        lastNameEdit.setText(userEditTarget.getLastName());
        userNameEdit.setText(userEditTarget.getUsername());
        passwordEdit.setText(userEditTarget.getPassword());

        //Save the "old" data
        oldFirstname = userEditTarget.getFirstName();
        oldLastname = userEditTarget.getLastName();
        oldUsername = userEditTarget.getUsername();
        oldPassword = userEditTarget.getPassword();


        //When Update Profile Button clicked
        buttUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Grab data from all 4 text fields & assign to temp variables
                newFirstname = firstNameEdit.getText().toString();
                newLastname = lastNameEdit.getText().toString();
                newUsername = userNameEdit.getText().toString();
                newPassword = passwordEdit.getText().toString();

                //Call Update Function for Usernames
                userObj.updateFromUsername(newUsername, oldUsername);
                courseObj.updateFromUsername(newUsername, oldUsername);
                assignObj.updateFromUsername(newUsername, oldUsername);
                catObj.updateFromUsername(newUsername, oldUsername);
                enrollObj.updateFromUsername(newUsername, oldUsername);

                //Now Update Password, Firstname and Lastname
                userObj.updatePassFromUser(newPassword, newUsername);
                userObj.updateFNFromUser(newFirstname, newUsername);
                userObj.updateLNFromUser(newLastname, newUsername);

                //Toast!
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "Updated! Sending back to login!" , Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                //Send back to login screen
                Intent intentEditToLogin = new Intent(EditUserActivity.this, LoginActivity.class);
                startActivity(intentEditToLogin);

            }
        });


    }

}
