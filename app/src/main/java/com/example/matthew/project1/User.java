package com.example.matthew.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class User{
    private Course courseList;
    private int length;
    public User(){
        courseList = new Course();
        length = 0;
    }
    public void addCourse(String name){
        Course toAdd = new Course(name);
        toAdd.setNext(courseList.getNext());
        courseList.setNext(toAdd);
        length+=1;
    }
    public Course getCourseList(){
        return courseList;
    }
    public String[] getCourseNames(){
        String[] toReturn = new String[length];
        Course traverse = courseList.getNext();
        for(int i=0;i<length;i++){
            toReturn[i] = traverse.getCourseName();
            traverse=traverse.getNext();
        }
        return toReturn;
    }
}
