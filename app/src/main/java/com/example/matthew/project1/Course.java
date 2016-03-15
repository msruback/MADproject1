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
    public void addCourseContact(CourseContact toAdd){
        toAdd.setNext(contactList.getNext());
        contactList.setNext(toAdd);
        return;
    }
    public void removeCourseContact(CourseContact toRemove){
        while(contactList.getNext()!=null){
            if(toRemove.equals(contactList.getNext())){
                contactList.setNext(contactList.getNext().getNext());
                return;
            }
        }
        return;
    }
    public CourseContact getContactList(){
        return contactList;
    }
    public String[] getContactNames(){
        String[] toReturn = new String[length];
        CourseContact traverse = contactList.getNext();
        for(int i=0;i<length;i++){
            toReturn[i] = traverse.getName();
            traverse=traverse.getNext();
        }
        return toReturn;
    }
}
