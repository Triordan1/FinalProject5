package com.example.finalproject5;

/**
 * A model for the items in the adapter.
 * The "UserActivity" class will hold a list of these items
 * and each item will hold the fields below such as courseID and className.
 * @author Johnny Huynh
 */
public class ItemModel {

    //Fields in the item
    private int courseID;
    private String className;
    private String classInstructor;
    private double grade;
    private String username;

    //Constructor
    public ItemModel (int id, String className, String classInstructor, double grade, String username) {
        this.courseID = id;
        this.className = className;
        this.classInstructor = classInstructor;
        this.grade=grade;
        this.username = username;
    }

    //Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassInstructor(String classInstructor) {
        this.classInstructor = classInstructor;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Getters
    public String getClassName() {
        return className;
    }

    public String getClassInstructor() {
        return classInstructor;
    }
    public String getGrade(){
        return Double.toString(grade);
    }
    public int getCourseID() {
        return courseID;
    }
    public String getUsername() {
        return username;
    }
}
