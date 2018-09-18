/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * 這個Class目的是要呼叫QuerryUtils Class中的ArrayList，並呼叫EarthquakeAdapter Class中的Adapter(調度器)，
 * 然後把Adapter(調度器)設置到(對接到)earthquake_activity佈局中的ListView，在ListView上顯示ArrayList。
 * 此外也要在該ListView上設置OnItemClickListener，在使用者點擊時，透過Intent叫出網址頁面。
 */


public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Call extractEarthquakes() method from the QueryUtils class to get the list of earthquakes.
        ArrayList<Earthquake> earthquakeArrayList = QueryUtils.extractEarthquakes();

        // Create a new adapter that takes the list of earthquakes as input.
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakeArrayList);

        // Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the ListView so the list can be populated in the user interface.
        earthquakeListView.setAdapter(adapter);


        /**
         * Once the earthquake URL is properly stored in the Earthquake objects, we can access the URL when the list item is clicked.
         * From the last course, we learned how to detect and respond when a user taps on a list item. We need to declare an OnItemClickListener on the ListView.
         * OnItemClickListener is an interface, which contains a single method onItemClick().
         * We declare an anonymous class that implements this interface, and provides customized logic for what should happen in the onItemClick() method.
         * Remember that the onItemClick() method is a callback triggered by the Android system when the user clicks on a list item.
         *
         * We override the onItemClick() method, so that when a list item is clicked, we find the corresponding Earthquake object from the adapter.
         * (Note that we also had to add the “final” modifier on the EarthquakeAdapter local variable, so that we could access the adapter variable within the OnItemClickListener.)
         */
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor).
                // Create a new intent to view the earthquake URI. The Intent constructor (that we want to use) requires a Uri object,
                // so we need to convert our URL (in the form of a String) into a URI.
                // We know that our earthquake URL is a more specific form of a URI, so we can use the Uri.parse method.
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());

                // Once we have the website URL in a Uri object, we can create a new intent.
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity.
                // Lastly, we start a new activity with that intent, so that a web browser app on the device will handle the intent and display the website for that earthquake.
                startActivity(websiteIntent);
            }
        });
    }
}
