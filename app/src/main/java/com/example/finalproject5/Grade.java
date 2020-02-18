package com.example.finalproject5;

import android.content.Context;

import com.example.finalproject5.Model.AppDatabase;
import com.example.finalproject5.Model.Assignment.Assignment;

import java.util.List;

public class Grade {

    public double getTestGrade(Context context, String username){
        String test = "test";
        double max = 0;
        double total =0 ;
        double grade;
        List<Assignment> scores = AppDatabase.getAppDatabase(context).assignmentDao().getAllTest(username,test);
        if (scores.size()!=0){
            for (Assignment a:scores
            ) {
                max = max +  a.getMaxScore();
                total = total + a.getEarnedScore();
            }
            grade = total/max;

        }else{
            grade= 1;
        }

        return  grade;
    }
    public double getHwGrade(Context context,String username){
        String hw = "hw";
        double max = 0;
        double total =0 ;
        double grade;
        List<Assignment> scores = AppDatabase.getAppDatabase(context).assignmentDao().getAllHw(username,hw);
        if (scores.size()!=0){
            for (Assignment a:scores
            ) {
                max = max +  a.getMaxScore();
                total = total + a.getEarnedScore();
            }
            grade = total/max;
        }else{
            grade= 1;
        }

        return grade;
    }
    public double getQuizGrade(Context context,String username){
        String quiz = "quiz";
        double max = 0;
        double total =0 ;
        double grade;
        List<Assignment> scores = AppDatabase.getAppDatabase(context).assignmentDao().getAllQuiz(username,quiz);
        if (scores.size()!=0){
            for (Assignment a:scores
            ) {
                max = max +  a.getMaxScore();
                total = total + a.getEarnedScore();
            }
            grade = total/max;
        } else{
            grade= 1;
        }
        return  grade;
    }
    public double getGrade(Context context,String username){
        double testScore;
        double hwScore;
        double quizeScore;
        double grade;


        hwScore = getHwGrade(context,username);
        testScore = getTestGrade(context,username);
        quizeScore = getQuizGrade(context,username);
        grade= ( testScore * 20)+(hwScore*50)+(quizeScore*30);



        return grade;
    }
}
