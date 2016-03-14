package com.example.matthew.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class Course{
    private String courseName;
    private Course next;
    private CourseContact contactList;
    private int length;
    public Course(){
        courseName = "head";
        next = null;
        contactList = new CourseContact();
        length = 0;
    }
    public Course(String name){
        courseName = name;
        next = null;
        contactList = new CourseContact();
        length = 0;
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
