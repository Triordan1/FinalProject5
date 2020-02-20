package com.example.finalproject5.Model.Instructor;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.finalproject5.Model.AppDatabase;

/** This class is the entity used to hold the instructor details. */

@Entity(tableName = AppDatabase.INSTRUCTOR_TABLE)
public class Instructor {
    @PrimaryKey(autoGenerate = true)
    private int instructorID;

    private String firstName;
    private String lastName;

    public Instructor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
