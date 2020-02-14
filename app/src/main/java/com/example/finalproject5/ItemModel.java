package com.example.finalproject5;

public class ItemModel {

    //Fields in the item
    private String className;
    private String classInstructor;
    double grade;

    //Constructor
    public ItemModel (String className, String classInstructor,double grade) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.grade=grade;
    }

    //Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassInstructor(String classInstructor) {
        this.classInstructor = classInstructor;
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
}
