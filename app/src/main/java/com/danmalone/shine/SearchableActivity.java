package com.danmalone.shine;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;

import org.androidannotations.annotations.EBean;

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


    void doMySearch(String query){

    }
}
