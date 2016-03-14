package com.example.matthew.project1;

import android.graphics.Path;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 3/10/2016.
 */
public class CourseContact implements Parcelable {

    private String name,email;
    private Uri photo;
    private CourseContact next;

    public CourseContact(){
        name="head";
        email=null;
        photo=null;
        next=null;
    }

    public CourseContact(String setName, String setEmail){
        name = setName;
        email = setEmail;
        photo = null;
        next = null;
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

    public Uri getPhoto(){
        return photo;
    }

    public void setPhoto(Uri toSet){
        photo = toSet;
    }

    public CourseContact getNext(){
        return next;
    }

    public void setNext(CourseContact toSet){
        next = toSet;
    }

    protected CourseContact(Parcel in) {
        name = in.readString();
        email = in.readString();
        photo = (Uri) in.readParcelable(Uri.class.getClassLoader());
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
        dest.writeParcelable(photo, 0);
    }
}