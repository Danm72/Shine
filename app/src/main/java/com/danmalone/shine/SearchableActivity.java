package com.danmalone.shine;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ItemClick;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by danmalone on 14/09/2014.
 */

@EActivity(R.layout.activity_searchable)
public class SearchableActivity extends Activity implements SearchListFragment_.Callbacks {

    @FragmentById(R.id.search_frame)
    SearchListFragment search;

    List<Address> addresses = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_searchable);

        // Get the intent, verify the action and get the query
        handleIntent(getIntent());
    }

    @AfterViews
    public void after() {
        if (search != null) {
            search.addToAdapter(addresses);
        }
    }

    @AfterInject
    public void inject() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }


    @Background
    void doMySearch(String query) {
        Geocoder geocoder =
                new Geocoder(this, Locale.getDefault());

        try {
            if (query != null)
                addresses = geocoder.getFromLocationName(query, 4);
            if (search != null)
                search.addToAdapter(addresses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(Address location) {

    }
}
