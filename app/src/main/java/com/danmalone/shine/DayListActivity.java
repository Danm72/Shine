package com.danmalone.shine;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.danmalone.shine.adapters.TabsAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;


/**
 * An activity representing a list of Days. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DayListFragment} and the item details
 * (if present) is a {@link DayDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link DayListFragment.Callbacks} interface
 * to listen for item selections.
 */
@EActivity(R.layout.activity_day_list)
@OptionsMenu(R.menu.menu)
public class DayListActivity extends ActionBarActivity
        implements DayListFragment.Callbacks {

    @ViewById
    ViewPager pager;
    TabsAdapter mTabsAdapter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pager = new ViewPager(this);
//        mViewPager.setId(R.id.pager);

//        setContentView(R.layout.activity_day_list);


//        getActionBar().setTitle("Title");

        if (findViewById(R.id.day_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((DayListFragment_) getSupportFragmentManager()
                    .findFragmentById(R.id.day_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    @AfterInject
    void afterInj() {

    }

    @AfterViews
    void afterV() {
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        mTabsAdapter = new TabsAdapter(this, pager);

        mTabsAdapter.addTab(bar.newTab().setText("Dublin"),
                DayListFragment_.class, "Dublin,IE", null);
        mTabsAdapter.addTab(bar.newTab().setText("Barcelona"),
                DayListFragment_.class, "Barcelona,ES", null);
        mTabsAdapter.addTab(bar.newTab().setText("New York"),
                DayListFragment_.class, "New York,US", null);
        mTabsAdapter.addTab(bar.newTab().setText("London"),
                DayListFragment_.class, "London,GB", null);

        pager.setOffscreenPageLimit(4);

        pager.setAdapter(mTabsAdapter);
    }

    /**
     * Callback method from {@link DayListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DayDetailFragment.ARG_ITEM_ID, id);
            DayDetailFragment fragment = new DayDetailFragment_();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.day_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DayDetailActivity_.class);
            detailIntent.putExtra(DayDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}