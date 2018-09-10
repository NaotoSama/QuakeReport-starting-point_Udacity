package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 這個Adapter(調度器)的Class目的是要對接自定義的custom_earthquake_list_item佈局，以及對接ArrayList，並把ArrayList中擺放好的各模具設置到(對接到)相對應的View上。
 * 這其中另外包括將"時間"要素的秒數格式化成常人可理解的時間日期格式。
 */


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Two helper methods, formatDate() and formatTime(), that we created to accept a Date object and return an appropriately formatted date string using SimpleDateFormat.
     * These two methods are used in the lower part of the getView method below where we produce the formatted date and time strings to display in the corresponding TextViews
     **/
    // Return the formatted date string (i.e. "Mar 3, 1984") from a Date object. 利用SimpleDateFormat來自定義日期格式
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy"); // stand-alone month => L:1 LL:01 LLL:Jan LLLL:January LLLLL:J
        return dateFormat.format(dateObject);
    }

    // Return the formatted date string (i.e. "4:30 PM") from a Date object. 利用SimpleDateFormat來自定義時間格式
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a"); //a代表AM或PM
        return timeFormat.format(dateObject);
    }


    /**
     * Creat a helper method called formatMagnitude() that takes a double value as input and returns the formatted string.
     * The helper method initializes a DecimalFormat object instance with the pattern string “0.0”.
     * Then in the getView() method of the adapter, we can read the magnitude value from the current Earthquake object,
     * format the decimal into a string, and then update the TextView to display the value.
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2") from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


    /**
     * We will be using the split(String string) method in the String class to split the original string at the position where the text “ of “ occurs.
     * The result will be a String containing the characters PRIOR to the “ of ” text and a String containing the characters AFTER the “ of “ text.
     * Since we’ll frequently need to refer to the “ of “ text, we can define a static final String constant (that is a global variable) at the top of the EarthquakeAdapter class.
     **/
    private static final String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakeArrayList) {
        super(context, 0, earthquakeArrayList);
    }


    /**
     * Create a getView method to control how the listView gets created.
     * */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {                                                                  // When getView is called, we can check to see if we can use a recycled view.
            listItemView = LayoutInflater.from(getContext()).inflate(                               // Otherwise, we inflate a new ListItem layout defined in the custom_earthquake_list_item.xml file.
                    R.layout.custom_earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);                                           // We can use the position parameter passed in to get a reference to the appropriate Earthquake object from the list of earthquakes.

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.earthquake_magnitude); //Bind the data from the Earthquake object to the views in the custom_earthquake_list_item layout, and set the corresponding data onto them.
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeTextView.setText(formattedMagnitude);


        /**
         * We get the original location String from the Earthquake object and store that in a variable.
         * We also create new variables (primary location and location offset) to store the resulting Strings.
         **/
        String originalLocation = currentEarthquake.getmLocation();
        String primaryLocation;
        String locationOffset;


        /**
         * Let’s dive into the details of the split. If the original location String is “74km NW of Rumoi, Japan” and we split the string using the LOCATION_SEPARATOR,
         * then we will get a String array as the return value. In the String array, the 0th element of the array is “74km NW” and the 1st element of the array is “Rumoi, Japan”.
         * Note that we also add the “ of “ text back to the 0th element of the array, so the locationOffset will say “74km NW of “.
         * There is still the issue that some location Strings don’t have a location offset. Hence, we should check if the original location String contains the LOCATION_SEPARATOR first,
         * before we decide to split the string with that separator. If there is no LOCATION_SEPARATOR in the original location String,
         * then we can assume that we should we use the “Near the” text as the location offset, and just use the original location String as the primary location.
         **/
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        /**
         * Once we have the 2 separate Strings, we can display them in the 2 TextViews in the list item layout.
         **/
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);



        /**
         * Within the EarthquakeAdapter, we write the following code to produce the formatted strings to display in the corresponding TextViews.
         * We get the time from the current Earthquake object, using currentEarthquake.getTimeInMilliseconds(),
         * and pass that into the Date constructor to form a new Date object.
         **/
        // First create a new Date object from the time in milliseconds of the earthquake. The Date object requires a long (here it is "getmTimeInMilliseconds") as input.
        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());
        // Then Format the date string (i.e. "Mar 3, 1984") 呼叫formatDate method並導入dateObject，並使之變成名為formattedDate的String
        String formattedDate = formatDate(dateObject);  // formatDate method的定義在最上方，利用SimpleDateFormat來自定義日期格式
        // Find the TextView with view ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);
        // Then Format the time string (i.e. "4:30PM") 呼叫formatTime method並導入dateObject，並使之變成名為formattedTime的String
        String formattedTime = formatTime(dateObject);   // formatDate method的定義在最上方，利用SimpleDateFormat來自定義時間格式
        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        //Once everything is set, return the view to the caller, which is the ListView that will take all the list items and display them on the screen.
        return listItemView;
    }

}
