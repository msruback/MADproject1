package com.example.matthew.project1;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class CourseContact implements Parcelable {

    private String name,email,course;
    private CourseContact next;

    public CourseContact(){
        name=null;
        email=null;
        next=null;
        course=null;
    }


    public String getName(){
        return name;
    }

    public void setName(String toSet){
        name = toSet;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String toSet){
        email = toSet;
    }


    public CourseContact getNext(){
        return next;
    }

    public void setNext(CourseContact toSet){
        next = toSet;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String toSet){
        course = toSet;
    }

    protected CourseContact(Parcel in) {
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<CourseContact> CREATOR = new Creator<CourseContact>() {
        @Override
        public CourseContact createFromParcel(Parcel in) {
            return new CourseContact(in);
        }

        @Override
        public CourseContact[] newArray(int size) {
            return new CourseContact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
    }
}
