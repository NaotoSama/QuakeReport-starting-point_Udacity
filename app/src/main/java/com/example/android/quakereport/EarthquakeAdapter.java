package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

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

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.earthquake_place);
        placeTextView.setText(currentEarthquake.getmPlace());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.earthquake_date);
        dateTextView.setText(currentEarthquake.getmDate());

        return listItemView;                                                                        //Once everything is set, return the view to the caller, which is the ListView that will take all the list items and display them on the screen.
    }

}
