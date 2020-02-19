package com.example.finalproject5;

import android.content.Context;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Category.Category;

import java.util.List;

public class Grade {


    private double getCategoryGrade(Context context, String username, Category category, String courseTittle) {
        double grade =0;

        // hold max grade and earned for each assignment
        int maxScore=0;
        int scoreEarnd=0;
        double weight ;

        // get list of all Assignments for this category
        List<Assignment> assignments = AppDatabase.getAppDatabase(context).assignmentDao().getAll(username,category.getTitle(),courseTittle);

        if(assignments.size()!=0){

            for (Assignment asg: assignments) {
                scoreEarnd += asg.getEarnedScore();
                maxScore += asg.getMaxScore();
            }
            weight= (double)category.getWeight()/100;
            grade = (double) scoreEarnd/maxScore;
            grade = grade* weight;
            return grade;
        }else {
            return grade ;
        }

    }


    public double getGrade(Context context,String username,String courseTitle){

        double grade=0;
        double score;
        // get list of all categories for this user
        List<Category> categories = AppDatabase.getAppDatabase(context).categoryDao().getAllCategories(username,courseTitle);
        if (categories.size()!= 0 ){
            /// get grade for each category and add it to running total
            for (Category cat: categories) {

                score = getCategoryGrade(context ,username, cat, courseTitle );
                grade = grade + score;
            }
            return grade * 100;
        }else {
            return 0;
        }
    }
}
