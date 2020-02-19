package com.example.finalproject5.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.Category;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.R;

public class AssignmentDetails extends AppCompatActivity {

    String score;
    String categorySetter = "work in progress";
    String currentUser;
    int mAssignmentId;
    int mCourseId;

    AssignmentDao mAssignmentDao;
    CategoryDao mCategoryDao;
    CourseDao mCourseDao;

    EditText etAssignment;
    EditText etScore;
    EditText etDetails;
    EditText etAssigned;
    EditText etDue;

    TextView tvAssigned;
    TextView tvDue;
    TextView tvCategory;

    Button btEdit;
    Button btDelete;

    ImageButton btGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);

        mAssignmentId = getIntent().getIntExtra("Assignment", 0);
        mCourseId = getIntent().getIntExtra("Course", 0);
        currentUser = getIntent().getStringExtra("User");

        mAssignmentDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();
        final Assignment assignment = mAssignmentDao.getAssignmentByID(mAssignmentId);

        mCategoryDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .categoryDao();
        Category category = mCategoryDao.getCategoryByID(assignment.getCategoryID());

        mCourseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .courseDao();
        final Course course = mCourseDao.getCourseFromID(mCourseId);

        etAssignment = findViewById(R.id.etAssignmentName);
        etAssignment.setText(assignment.getAssignmentName());

        etScore = findViewById(R.id.etScore);
        score = assignment.getEarnedScore() + "/" + assignment.getMaxScore();
        etScore.setText(score);

        etDetails = findViewById(R.id.etDetails);
        etDetails.setText(assignment.getDetails());

        etAssigned = findViewById(R.id.etAssigned);
        etAssigned.setText(assignment.getAssignedDate());

        etDue = findViewById(R.id.etDue);
        etDue.setText(assignment.getDueDate());

        tvAssigned = findViewById(R.id.tvAssigned);
        tvDue = findViewById(R.id.tvDue);

        tvCategory = findViewById(R.id.tvCategory);
        // needs to be fixed
//        categorySetter = category.getTitle() + " (" + category.getWeight() + "%)";
        tvCategory.setText(categorySetter);

        btDelete = findViewById(R.id.btDelete);
        btEdit = findViewById(R.id.btEdit);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btEdit.getText().equals("Edit")) {
                    etAssignment.setEnabled(true);
                    etScore.setEnabled(true);
                    etDetails.setEnabled(true);
                    etAssigned.setEnabled(true);
                    etDue.setEnabled(true);

                    btEdit.setText("Done");
                } else {
                    mAssignmentDao.update(assignment);
                    mCourseDao.update(course);

                    etAssignment.setEnabled(false);
                    etScore.setEnabled(false);
                    etDetails.setEnabled(false);
                    etAssigned.setEnabled(false);
                    etDue.setEnabled(false);

                    btEdit.setText("Edit");
                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete assignment
            }
        });

        btGoBack = findViewById(R.id.btGoToAssignment);
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignmentDetails.this, CourseView.class);
                intent.putExtra("User", currentUser);
                String name = course.getTitle();
                intent.putExtra("Course", name);
                startActivity(intent);
            }
        });

    }
}
