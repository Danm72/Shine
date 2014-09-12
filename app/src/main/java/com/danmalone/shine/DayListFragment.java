package com.danmalone.shine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.danmalone.shine.adapters.DayListAdapter;
import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.DailyForecast;
import com.danmalone.shine.api.models.Forecast;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.OWMClient.BASE_URL;

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

    OWMClient client;

    @ViewById
    ListView list;

    @Bean
    DayListAdapter adapter;

    @FragmentArg("location")
    String location;


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
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
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
                .setEndpoint(BASE_URL)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        client = restAdapter.create(OWMClient.class);

        if(location != null)
            attemptAPICall(client, location);

        list.setAdapter(adapter);
    }


    @AfterInject
    void test() {
    }


    @ItemClick
    void listItemClicked(DayListModel person) {
        mCallbacks.onItemSelected("1");
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
        Forecast forecast = client.forcastWeatherAtCity(location);

        updateView(forecast);
    }

    @UiThread
    void updateView(Forecast forecast) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        for (DailyForecast dailyForecast : forecast.getList())
            try {
                Date date = fmt.parse(dailyForecast.getDtTxt());
                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_WEEK);
                DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
                String dayOfMonthStr = symbols.getWeekdays()[day];
                double temp = dailyForecast.getMain().getTemp();
                adapter.add(new DayListModel(dayOfMonthStr, temp + "", temp > 20 ? R.drawable.sunny : R.drawable.chance_of_rain));
                adapter.notifyDataSetChanged();
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }
}
