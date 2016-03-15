package com.example.matthew.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class Course{
    private String courseName;
    private Course next;
    public Course(){
        courseName = "head";
        next = null;
    }
    public Course(String name){
        courseName = name;
        next = null;
    }
    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String name){
        courseName=name;
    }
    public Course getNext(){
        return next;
    }
    public void setNext(Course toSet){
        next = toSet;
    }

}
