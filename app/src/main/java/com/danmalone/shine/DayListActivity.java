package com.danmalone.shine;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.danmalone.shine.adapters.SearchCursor;
import com.danmalone.shine.adapters.TabsAdapter;
import com.danmalone.shine.dao.AddressDAO;
import com.danmalone.shine.dao.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


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
public class DayListActivity extends FragmentActivity
        implements DayListFragment_.Callbacks, SearchCursor.Callbacks {

    public DatabaseHelper dbHelper = null;

    SearchView searchView;

    @OptionsMenuItem(R.id.action_dismiss)
    MenuItem action_dismiss;

    @OptionsItem
    void action_dismiss() {
        int currentItem = pager.getCurrentItem();
        String name = mTabsAdapter.currentTab(currentItem).name.split(",")[0];
        RuntimeExceptionDao<AddressDAO, Integer> simpleDataDao = ((DayListActivity_) this).dbHelper.getSimpleDataDao();
        QueryBuilder<AddressDAO, Integer> queryBuilder =
                simpleDataDao.queryBuilder();
        PreparedQuery<AddressDAO> preparedQuery = null;

        try {
            queryBuilder.where().eq(AddressDAO.ADDRESS_NAME, name);
            preparedQuery = queryBuilder.prepare();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<AddressDAO> accountList = simpleDataDao.query(preparedQuery);
        simpleDataDao.delete(accountList.get(0));
        mTabsAdapter.removeItem(currentItem);
        mTabsAdapter.notifyDataSetChanged();
        pager.setAdapter(mTabsAdapter);
    }

    @ViewById
    ViewPager pager;
    TabsAdapter mTabsAdapter;

    SearchCursor cursorAdapter;
    ActionBar bar;

/*    @OrmLiteDao(helper = DatabaseHelper.class, model = AddressDAO.class)
    AddressDAO userDao;*/

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
       /* final boolean customTitleSupported =
                requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.titlebar);
        }*/
    }

    @AfterViews
    void afterV() {
        bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        mTabsAdapter = new TabsAdapter(this, pager);

        SharedPreferences prefs = this.getSharedPreferences(
                "com.danmalone.shine", Context.MODE_PRIVATE);

        Boolean firstrun = prefs.getBoolean("Firstrun", true);

        dbHelper = getHelper();

        RuntimeExceptionDao<AddressDAO, Integer> simpleDataDao = dbHelper.getSimpleDataDao();


        if (firstrun) {
            prefs.edit().putBoolean("Firstrun", false).apply();

            simpleDataDao.createIfNotExists(new AddressDAO("Dublin", "IE", "Ireland"));
            simpleDataDao.createIfNotExists(new AddressDAO("Barcelona", "ES", "Spain"));
            simpleDataDao.createIfNotExists(new AddressDAO("New York", "US", "America"));
            simpleDataDao.createIfNotExists(new AddressDAO("London", "GB", "United Kingdom"));
        }

        refreshTabs();
    }

    private void refreshTabs() {
        RuntimeExceptionDao<AddressDAO, Integer> simpleDataDao = dbHelper.getSimpleDataDao();

        List<AddressDAO> addy = simpleDataDao.queryForAll();

        for (AddressDAO address : addy) {
            mTabsAdapter.addTab(bar.newTab().setText(address.getCountry()),
                    DayListFragment_.class, address.getCountry() + "," + address.getCode(), null);
        }

        pager.setOffscreenPageLimit(4);

        pager.setAdapter(mTabsAdapter);
    }

    /**
     * Callback method from {@link DayListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String location, String day) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

            Fragment fragment = DayDetailFragment_.builder().location(location).day(day).build();

            getFragmentManager().beginTransaction()
                    .replace(R.id.day_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DayDetailActivity_.class);
            detailIntent.putExtra("Location", location);
            detailIntent.putExtra("Day", day);

            startActivity(detailIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    private DatabaseHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = (DatabaseHelper) OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dbHelper;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();

        int searchTextViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchTextView = (TextView) searchView.findViewById(searchTextViewId);
        searchTextView.setHintTextColor(getResources().getColor(R.color.white));
        searchTextView.setTextColor(getResources().getColor(R.color.white));

        searchItem.getIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.LIGHTEN);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                loadData(query, menu);
                return true;
            }

        });

        return true;
    }

    private void loadData(String query, Menu menu) {
        List<Address> addresses = null;

        Geocoder geocoder =
                new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocationName(query, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] columns = new String[]{"_id", "text"};
        Object[] temp = new Object[]{0, "default"};

        MatrixCursor cursor = new MatrixCursor(columns);

        for (int i = 0; i < addresses.size(); i++) {
            temp[0] = i;
            temp[1] = addresses.get(i);

            cursor.addRow(temp);
        }

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName cn = new ComponentName(this, SearchableActivity_.class);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(cn));

        cursorAdapter = new SearchCursor(this, cursor, addresses, dbHelper);

        searchView.setSuggestionsAdapter(cursorAdapter);
    }


    @Override
    public void onItemSelected(Address location) {
        dbHelper.getSimpleDataDao().createIfNotExists(new AddressDAO(location.getFeatureName(), location.getCountryCode(), location.getCountryName()));
        mTabsAdapter.addTab(bar.newTab().setText(location.getFeatureName()),
                DayListFragment_.class, location.getCountryName() + "," + location.getCountryCode(), null);
        mTabsAdapter.notifyDataSetChanged();

    }
}