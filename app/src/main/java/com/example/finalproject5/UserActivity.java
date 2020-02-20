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
import androidx.room.Room;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the UserActivity.  It will list the user's courses in a Recycler View.
 * Each Course item will have its' Class name, class instructor and the users current grade.
 * @author Johnny Huynh
 *
 */

public class UserActivity extends AppCompatActivity {

    //Table Objects
    UserDao userObj;
    CourseDao courseObj;
    AssignmentDao assignObj;
    CategoryDao catObj;

    //Create RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recAdapter;

    //Create List for itemModel
    List<ItemModel> listItems;
    List<Course> possibleCourses;

    //Transferred Data from other screen
    static String currentUser;
    String sentFirstName;
    String sentLastName;

    //Temp Variables
    String tempUsername;
    String tempInstructor;
    int tempID;
    String tempCourseName;


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
                Intent intentUserToCourse = new Intent(UserActivity.this, Create_Class.class);
                intentUserToCourse.putExtra("LoggedInUser", currentUser);
                startActivity(intentUserToCourse);
                return true;

            //Edit User Button
            case R.id.itemEdit:
                //Send to Edit User Activity
                Intent intentUserToEdit = new Intent(UserActivity.this, EditUserActivity.class);

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

        currentUser = getIntent().getStringExtra("LoggedInUser");

        //Create table DB instances
        userObj = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .dao();

        //Get user from table
        User tempUser = userObj.getUserFromName(currentUser);

        courseObj = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .courseDao();

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




        //Loop through all courses with user name
        for (Course tempCourse: userCourses) {
            double grade= gradeGet.getGrade(UserActivity.this,findUser,tempCourse.getCourseID());
            //Create a new item list Entry
            ItemModel singleItem = new ItemModel(tempCourse.getCourseID(), tempCourse.getTitle(), tempCourse.getInstructor(), grade,tempCourse.getUsername());
            listItems.add(singleItem);
        }
    }


    /**
     * This is for when users swipe right on the item, AKA course.
     * Swiping Right will delete the course and the corresponding entities from the connected tables.
     */
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

            //Return Course ID, Note* in the event of duplicates, it will remove
            Course tempCourse = courseObj.getCourseUI(tempUsername, tempInstructor);

            //Get CourseID for assignments and categories
            tempID = tempCourse.getCourseID();

            //Get Course Name
            tempCourseName = tempCourse.getTitle();

            //Delete From Course Table
            courseObj.deleteFromSwipe( tempUsername, tempInstructor);

            //Now Delete from corresponding tables: Assignments, Categories
            assignObj.deleteFromSwipe(tempUsername, tempID);
            catObj.deleteFromSwipe(tempUsername, tempCourseName);

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
