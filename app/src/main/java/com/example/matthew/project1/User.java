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
    public String[] getContactNames(){
        String [] toReturn, workingArray1, workingArray2;
        toReturn = new String[0];
        Course traverse = courseList.getNext();
        for(int i=0;i<length;i++){
            workingArray1=traverse.getContactNames();
            workingArray2=toReturn;
            toReturn=new String[workingArray1.length+workingArray2.length];
            for(int j=0;j<toReturn.length;j++){
                if(j<workingArray1.length-1){
                    toReturn[j]=workingArray1[j];
                }else{
                    toReturn[j]=workingArray2[j-workingArray1.length];
                }
            }
        }
        return toReturn;
    }
    public CourseContact findContact(String name){
        Course traverseCourse = courseList;
        CourseContact traverseContact, returnContact = new CourseContact();
        while(traverseCourse.getNext()!=null){
            traverseCourse = courseList.getNext();
            traverseContact = traverseCourse.getContactList();
            while(traverseContact.getNext()!=null){
                if(traverseContact.getName()==name){
                    returnContact=traverseContact;
                }
            }
        }
        return returnContact;
    }
}
