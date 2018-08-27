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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakeArrayList = new ArrayList<>();
        earthquakeArrayList.add(new Earthquake("7.2", "San Francisco", "2000/01/03"));
        earthquakeArrayList.add(new Earthquake("5.6","London", "2002/05/19"));
        earthquakeArrayList.add(new Earthquake("3.8","Tokyo", "2011/04/30"));
        earthquakeArrayList.add(new Earthquake("7.0", "Mexico", "2012/02/06"));
        earthquakeArrayList.add(new Earthquake("1.1","Moscow", "2013/10/16"));
        earthquakeArrayList.add(new Earthquake("6.6","Rio de Janeiro", "2015/07/14"));
        earthquakeArrayList.add(new Earthquake("3.3","Paris", "2017/12/29"));


        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakeArrayList);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView} so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
