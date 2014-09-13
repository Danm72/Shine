package com.danmalone.shine;

import android.app.Fragment;
import android.graphics.Color;
import android.widget.TextView;

import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.ForecastModels.DetailedForecast;
import com.danmalone.shine.api.models.ForecastModels.Forecast;
import com.danmalone.shine.dummy.DummyContent;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.OWMClient.BASE_URL;

/**
 * A fragment representing a single Day detail screen.
 * This fragment is either contained in a {@link DayListActivity}
 * in two-pane mode (on tablets) or a {@link DayDetailActivity}
 * on handsets.
 */
@EFragment(R.layout.fragment_day_detail)
public class DayDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    @ViewById
    TextView day_detail;

    @ViewById
    BarGraph graph;

    ArrayList<Bar> points = new ArrayList<Bar>();

    List<Reading> days;

    OWMClient client;

    @FragmentArg("location")
    String location;

    @FragmentArg("day")
    String day;

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayDetailFragment() {
    }

    @AfterInject
    void calledAfterInjection() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        client = restAdapter.create(OWMClient.class);
        days = new LinkedList<Reading>();

        attemptAPICall(client);
    }

    @AfterViews
    void calledAfterViewInjection() {
        getActivity().getActionBar().setTitle(location.split(",")[0]);
    }

    @Background
    void attemptAPICall(OWMClient client) {
//        Weather weather = client.getCityWeather("Dublin, Ireland");
        Forecast forecast = null;

        if (location != null)
            forecast = client.forcastWeatherAtCity(location);

        updateView(forecast);
    }

    @UiThread
    void updateView(Forecast forecast) {
        day_detail.setText(day);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (DetailedForecast dailyForecast : forecast.getList()) {
            Date date = null;
            try {
                date = fmt.parse(dailyForecast.getDtTxt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
            String dayOfMonthStr = symbols.getWeekdays()[day];
            double temp = dailyForecast.getMain().getTemp();

            if (dayOfMonthStr.equals(this.day))
                days.add(new Reading(dayOfMonthStr, temp, dailyForecast.getMain().getTempMin(), date.getHours(), ""));
        }

        for (Reading day : days) {
            Bar bar = new Bar();
            bar.setName(day.hour < 10 ? ("0" + day.hour + ":00") : (day.hour + ":00"));
            bar.setValue((int) day.maxTmp);
            bar.setColor(Color.parseColor("#4484f6"));
            bar.setLabelColor(Color.parseColor("#1485CC"));
            bar.setValueSuffix("°");
            points.add(bar);
        }
        graph.setBars(points);
    }

    private class Reading {
        String day;
        double maxTmp;
        double minTmp;
        int hour;
        String description;

        private Reading(String day, double maxTmp, double minTmp, int hour, String description) {
            this.day = day;
            this.maxTmp = maxTmp;
            this.minTmp = minTmp;
            this.hour = hour;
            this.description = description;
        }
    }
}
