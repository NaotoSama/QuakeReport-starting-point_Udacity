package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Two helper methods, formatDate() and formatTime(), that we created to accept a Date object and return an appropriately formatted date string using SimpleDateFormat.
     * These two methods are used in the lower part of the getView method below where we produce the formatted date and time strings to display in the corresponding TextViews
     **/
    // Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    // Return the formatted date string (i.e. "4:30 PM") from a Date object.
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


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
        magnitudeTextView.setText(currentEarthquake.getmMagnitude());

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.earthquake_location);
        placeTextView.setText(currentEarthquake.getmLocation());


    /**
     * Within the EarthquakeAdapter, we write the following code to produce the formatted strings to display in the corresponding TextViews.
     * We get the time from the current Earthquake object, using currentEarthquake.getTimeInMilliseconds(),
     * and pass that into the Date constructor to form a new Date object.
     **/
        // First create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());
        // Then Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Find the TextView with view ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);
        // Then Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        //Once everything is set, return the view to the caller, which is the ListView that will take all the list items and display them on the screen.
        return listItemView;
    }

}
