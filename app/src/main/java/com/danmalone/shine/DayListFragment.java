package com.danmalone.shine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import com.danmalone.shine.adapters.DayListAdapter;
import com.danmalone.shine.api.clients.WunderClient;
import com.danmalone.shine.api.models.wunder.daily.Forecastday_;
import com.danmalone.shine.api.models.wunder.daily.TenDay;
import com.danmalone.shine.models.DayListModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.WunderClient.API_KEY;
import static com.danmalone.shine.api.clients.WunderClient.BASE_URL;
import static java.lang.String.valueOf;

/**
 * A list fragment representing a list of Days. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link DayDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
@EFragment(R.layout.day_list)
public class DayListFragment extends Fragment {

    WunderClient wunder;

    @ViewById
    ListView list;

    @Bean
    DayListAdapter adapter;

    @FragmentArg("countyCode")
    String countryCode;

    @FragmentArg("countryName")
    String countryName;


    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id, String location);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id, String location) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayListFragment() {
    }

    @AfterViews
    void bindAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL + API_KEY)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        wunder = restAdapter.create(WunderClient.class);

        if (countryName != null)
            attemptAPICall(wunder, countryName, countryCode);

        list.setAdapter(adapter);
    }


    @AfterInject
    void test() {
    }


    @ItemClick
    void listItemClicked() {
        mCallbacks.onItemSelected(countryName, countryCode);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        list.setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            list.setItemChecked(mActivatedPosition, false);
        } else {
            list.setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    @Background
    void attemptAPICall(WunderClient client, String countryName, String countryCode) {
        TenDay forecastDaily = null;

        try {
            forecastDaily = client.getTenDayForecast(countryCode, countryName);
        } catch (Exception e) {
            Log.d("Shine", "Swallowing due to poor API response");
        }


        updateView(forecastDaily);
    }

    @UiThread
    void updateView(TenDay forecast) {
        if(forecast == null){
            return;
        }
        for (Forecastday_ dailyForecast : forecast.getForecast().getSimpleforecast().getForecastday()) {

            String maxTmp = dailyForecast.getHigh().getCelsius();
            String minTmp = dailyForecast.getLow().getCelsius();

            int drawable = decideOnIcon(dailyForecast.getIcon());
            String weekday = dailyForecast.getDate().getWeekday();
            adapter.add(new DayListModel(weekday, minTmp + "/" + maxTmp + "°", drawable));
            adapter.notifyDataSetChanged();
        }
    }

    int decideOnIcon(String forecast) {
        switch (valueOf(forecast)) {
            case "partlycloudy":
                return R.drawable.haze;
            case "cloudy":
                return R.drawable.cloudy;
            case "mostlycloudy":
                return R.drawable.cloudy;
            case "chancerain":
                break;
            case "clear":
                return R.drawable.sunny;
        }

        return R.drawable.sunny;
    }
}
