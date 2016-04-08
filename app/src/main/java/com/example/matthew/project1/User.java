package com.example.matthew.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class User{
    private Course courseList;
    private CourseContact contactList;
    private int courseLength,contactLength;
    public User(){
        courseList = new Course();
        courseLength = 0;
        contactList = new CourseContact();
        contactLength = 0;
    }
    public void addCourse(String name){
        Course toAdd = new Course(name);
        toAdd.setNext(courseList.getNext());
        courseList.setNext(toAdd);
        courseLength+=1;
    }
    public Course getCourseList(){
        return courseList;
    }
    public String[] getCourseNames(){
        String[] toReturn = new String[courseLength];
        Course traverse = courseList.getNext();
        for(int i=0;i<courseLength;i++){
            toReturn[i] = traverse.getCourseName();
            traverse=traverse.getNext();
        }
        return toReturn;
    }

    public void addCourseContact(CourseContact toAdd){
        toAdd.setNext(contactList.getNext());
        contactList.setNext(toAdd);
        contactLength+=1;
        return;
    }
    public void removeCourseContact(CourseContact toRemove){
        while(contactList.getNext()!=null){
            if(toRemove.equals(contactList.getNext())){
                contactList.setNext(contactList.getNext().getNext());
                contactLength-=1;
                return;
            }
        }
        return;
    }
    public CourseContact getContactList(){
        return contactList;
    }
    public String[] getContactNames(){
        String[] toReturn = new String[contactLength];
        CourseContact traverse = contactList.getNext();
        for(int i=0;i<contactLength;i++){
            toReturn[i] = traverse.getName();
            traverse=traverse.getNext();
        }
        return toReturn;
    }
    public CourseContact findContact(String name){
        CourseContact returnContact, traverse = contactList.getNext();
        for(int i=0;i<contactLength;i++){
            if(traverse.getName().equals(name)){
                return traverse;
            }
            traverse=traverse.getNext();
        }
        return new CourseContact();
    }
}
