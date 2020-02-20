package com.example.finalproject5;

import android.content.Context;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.Model.Category.Category;

import java.text.Format;
import java.util.List;

public class Grade {




    public double getGrade(Context context,String username,int courseID){

        double grade =0;
        double testgrade;
        double quizgrade;
        double hwgrade;
        double finalgrade;

        testgrade =(double) gradeType(username,courseID,"Test", context);
        quizgrade =(double)gradeType(username,courseID,"Quiz", context);
        hwgrade =(double) gradeType(username,courseID,"HW", context);
        finalgrade = (double)gradeType(username,courseID,"Final", context);

        testgrade = (double)testgrade *(20);
        quizgrade =(double) quizgrade *(20);
        hwgrade =(double) hwgrade*(40);
        finalgrade = (double)finalgrade *(20);

        grade= (double)(testgrade+quizgrade+hwgrade+finalgrade);




        return grade;

    }

    private double gradeType(String username, int courseID, String type,Context context) {
         double grade;
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
