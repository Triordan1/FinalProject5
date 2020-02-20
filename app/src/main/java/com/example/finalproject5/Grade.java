package com.example.finalproject5;

import android.content.Context;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Category.Category;

import java.util.List;

public class Grade {




    public double getGrade(Context context,String username,int courseID){

        double grade =0;
        double testgrade;
        double quizgrade;
        double hwgrade;
        double finalgrade;

        testgrade =gradeType(username,courseID,"Test", context);
        quizgrade =gradeType(username,courseID,"Quiz", context);
        hwgrade = gradeType(username,courseID,"HW", context);
        finalgrade = gradeType(username,courseID,"Final", context);

        testgrade = testgrade *(0.20);
        quizgrade = quizgrade *(0.20);
        hwgrade = hwgrade*(0.40);
        finalgrade = finalgrade *(0.20);

        grade= (testgrade+quizgrade+hwgrade+finalgrade)*100;


        return grade;

    }

    private double gradeType(String username, int courseID, String type,Context context) {
        double grade=0;
        // hold max grade and earned for each assignment
        int maxScore=0;
        int scoreEarnd=0;

        // get list of all Assignments for this category
        List<Assignment> assignments = AppDatabase.getAppDatabase(context).assignmentDao().getAll(username, type,courseID);

        if(assignments.size()!=0){

            for (Assignment asg: assignments) {
                scoreEarnd += asg.getEarnedScore();
                maxScore += asg.getMaxScore();
            }

            grade = (double) scoreEarnd/maxScore;

            return grade;
        }else {
            return 1 ;
        }


    }
    }
