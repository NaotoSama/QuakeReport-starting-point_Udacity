package com.example.android.quakereport;

public class Earthquake {

    private String mMagnitude;

    private String mPlace;

    private String mDate;


    //Call the constructor method to initialize the global member variables at the top based on the values we pass into the constructor.
    public Earthquake(String magnitude, String place, String date) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
    }


    //The global member variables at the top are private, so we need to create public Getter methods for other classes to access them.
    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmDate() {
        return mDate;
    }
}
