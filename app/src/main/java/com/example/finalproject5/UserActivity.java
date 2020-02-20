package com.example.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    //Table Objects
    UserDao userObj;
    CourseDao courseObj;

    //Create RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recAdapter;

    //Create List for itemModel
    List<ItemModel> listItems;
    List<Course> possibleCourses;

    //Transferred Data from other screen
    String currentUser;
    String sentFirstName;
    String sentLastName;

    //Temp Transfer Variables
    String tempUsername;
    String tempInstructor;

    //Puts items into appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_uatoolbar, menu);
        return true;
    }

    //Makes menu items clickable
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //Add Button
            case R.id.itemAdd:
                //For now it sends to MainActivity, will send to CreateClassPage in the future
                Intent intentUserToCourse = new Intent(UserActivity.this, Create_Class.class);
                startActivity(intentUserToCourse);
                return true;

            //Edit User Button
            case R.id.itemEdit:
                //Send to Edit User Activity
                Intent intentUserToEdit = new Intent(UserActivity.this, EditUserActivity.class);

                //Get current user from extras
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    currentUser = extras.getString("LoggedInUser");
                }

                //Send user as extra
                intentUserToEdit.putExtra("LoggedInUser", currentUser);

                //Go to edit activity
                startActivity(intentUserToEdit);
                return true;

            //Sign out Button
            case R.id.itemLast:
                //Sign out. Send back to Login Screen
                Intent intentUserToLogin = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intentUserToLogin);
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
        setContentView(R.layout.activity_user);

        //Create table DB instances
        userObj = AppDatabase.getAppDatabase(UserActivity.this).dao();
        courseObj = AppDatabase.getAppDatabase(UserActivity.this).courseDao();

        //Get Current User Logged in from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("LoggedInUser");
        }

        //Get user from table
        User tempUser = userObj.getUserFromName(currentUser);

        //Get from Getters
        sentFirstName = tempUser.getFirstName();
        sentLastName = tempUser.getLastName();

        //Create String for full name
        String fullname = "Student: " + sentFirstName + " " + sentLastName;

        //Set title to user's first name + last name
        setTitle(fullname);

        //Create Toolbar
        Toolbar uaToolbar = (Toolbar) findViewById(R.id.uaActivity_toolbar);
        setSupportActionBar(uaToolbar);

        //Connect RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set fixed size for items
        recyclerView.setHasFixedSize(true);

        //Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize lists
        listItems = new ArrayList<>();
        possibleCourses = new ArrayList<>();

        //Find users courses and add to itemlist to display
        checkCourses(currentUser);

        //Specify Adapter
        recAdapter = new uaAdapter(listItems, this);

        //Swipe
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        //Set Adapter to Recycler View
        recyclerView.setAdapter(recAdapter);


    }

    //Function: Checks Course Table for entries with String X
    public void checkCourses(String findUser) {

        //Get all courses
        List<Course> userCourses = courseObj.getAllCoursesWithUser(findUser);

        //Grade from Rod
        Grade gradeGet = new Grade();
        double grade= gradeGet.getGrade(UserActivity.this,findUser);

        //Loop through all courses with user name
        for (Course tempCourse: userCourses) {
            //Create a new item list Entry
            ItemModel singleItem = new ItemModel(tempCourse.getCourseID(), tempCourse.getTitle(), tempCourse.getInstructor(), grade,tempCourse.getUsername());
            listItems.add(singleItem);
        }
    }

    //Remove from list by swiping right
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //Get Swiped Position
            int position = viewHolder.getAdapterPosition();

            //Get info from list
            ItemModel tempItem = listItems.get(position);
            tempUsername = tempItem.getUsername();
            tempInstructor = tempItem.getClassInstructor();

            //Delete From database
            courseObj.deleteFromSwipe( tempUsername, tempInstructor);

            //Now Delete from corresponding tables: Assignments, Categories, Enrollment(?)


            //This just removes it from the view
            listItems.remove(viewHolder.getAdapterPosition());
            recAdapter.notifyDataSetChanged();

            //Toast Tell User Course was deleted
            Toast toast;
            toast = Toast.makeText(getApplicationContext(), "Course Deleted!" , Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    };
}
