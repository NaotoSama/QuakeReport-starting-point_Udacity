package com.example.android.quakereport;

/**
 * 這個自定義的Class目的是要定義模具和定義模具擺放的位置
 */


public class Earthquake {

    private String mMagnitude;

    private String mLocation;

    private long mTimeInMilliseconds;


    /**
     * Constructs a new {@link Earthquake} object.
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the earthquake happened
     */
    //Call the constructor method to initialize the global member variables at the top based on the values we pass into the constructor.
    public Earthquake(String magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }


    //The global member variables at the top are private, so we need to create public Getter methods for other classes to access them.
    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
