package com.example.finalproject5.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.Model.Category.CategoryDao;
import com.example.finalproject5.Model.Course.Course;
import com.example.finalproject5.Model.Course.CourseDao;
import com.example.finalproject5.Model.Enrollment.EnrollmentDao;
import com.example.finalproject5.Model.Grade.GradeDao;
import com.example.finalproject5.Model.User.User;
import com.example.finalproject5.Model.User.UserDao;

@Database(entities = {User.class, Course.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    public static final String dbName="db-gradechecker";
    public static final String USER_TABLE="user";
    public static final String COURSE_TABLE="course";
    public static final String ASSIGNMENT_TABLE="assignment";
    public static final String CATEGORY_TABLE="category";
    public static final String GRADE_TABLE="grade";
    public static final String ENROLLMENT_TABLE="enrollment";

    public abstract UserDao userDao();
    public abstract CourseDao courseDao();
    public abstract AssignmentDao assignmentDao();
    public abstract CategoryDao categoryDao();
    public abstract GradeDao gradeDao();
    public abstract EnrollmentDao enrollmentDao();

}
