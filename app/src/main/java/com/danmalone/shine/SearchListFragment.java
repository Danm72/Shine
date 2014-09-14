package com.danmalone.shine;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.ListView;

import com.danmalone.shine.adapters.DayListAdapter;
import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.DailyModels.DailyForecast;
import com.danmalone.shine.api.models.DailyModels.DailyWeather;
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

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.OWMClient.BASE_URL;

/**
 * A list fragment representing a list of Days. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link com.danmalone.shine.DayDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
@EFragment(R.layout.day_list)
public class SearchListFragment extends Fragment {

    @ViewById
    ListView list;

    @Bean
    DayListAdapter adapter;

    @FragmentArg("query")
    String query;


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
        public void onItemSelected(String location);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String location) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchListFragment() {
    }

    @AfterViews
    void bindAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        list.setAdapter(adapter);
    }


    @AfterInject
    void test() {
    }


    @ItemClick
    void listItemClicked(DayListModel day) {
        mCallbacks.onItemSelected(day.day);
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
    void attemptAPICall(OWMClient client, String location) {
//        Weather weather = client.getCityWeather("Dublin, Ireland");
//        Forecast forecast = client.forcastWeatherAtCity(location);
        DailyForecast forecastDaily = null;

        if (location != null) {
            forecastDaily = client.forecastWeatherAtCityDaily(location);
        }

//        forecastDaily.toString();
        updateView(forecastDaily);
    }

    @UiThread
    void updateView(DailyForecast forecast) {
        Calendar cal = Calendar.getInstance();

        for (DailyWeather dailyForecast : forecast.getList()) {
            Date date = new Date(dailyForecast.getDt() * 1000L);
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
            String dayOfMonthStr = symbols.getWeekdays()[day];
            int maxTmp = dailyForecast.getTemp().getMax().intValue();
            int minTmp = dailyForecast.getTemp().getMin().intValue();

            int drawable = decideOnIcon(dailyForecast);
            adapter.add(new DayListModel(dayOfMonthStr, minTmp + "/" + maxTmp + "°", drawable));
            adapter.notifyDataSetChanged();
        }
    }

    int decideOnIcon(DailyWeather forecast) {
        double temp = forecast.getTemp().getMax();
        int drawable;
        if (temp > 20) {
            String weather = forecast.getWeather().get(0).getMain();
            if (weather.equals("Clear"))
                drawable = R.drawable.sunny;
            else
                drawable = R.drawable.cloudy;
        } else
            drawable = R.drawable.haze;

        return drawable;
    }
}
