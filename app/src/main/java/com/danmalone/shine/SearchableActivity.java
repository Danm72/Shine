package com.danmalone.shine;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by danmalone on 14/09/2014.
 */

@EBean
public class SearchableActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);

            Fragment fragment = SearchListFragment_.builder().query(query).build();

            getFragmentManager().beginTransaction()
                    .replace(R.id.search_frame, fragment)
                    .commit();
        }
    }


    @Background
    void doMySearch(String query) {
        Geocoder geocoder =
                new Geocoder(this, Locale.getDefault());
        // Get the current location from the input parameter list
        // Create a list to contain the result address
        List<Address> addresses = null;
                /*
                 * Return 1 address.
                 */
        try {
            addresses = geocoder.getFromLocationName(query, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
