package com.danmalone.shine;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.widget.TextView;

import com.danmalone.shine.api.clients.WunderClient;
import com.danmalone.shine.api.models.wunder.hourly.Hourly;
import com.danmalone.shine.api.models.wunder.hourly.HourlyForecast;
import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.bounce.BounceEaseOut;
import com.db.chart.view.animation.easing.elastic.ElasticEaseOut;
import com.db.chart.view.animation.easing.quint.QuintEaseOut;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.WunderClient.API_KEY;
import static com.danmalone.shine.api.clients.WunderClient.BASE_URL;

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

    @ViewById(R.id.graph)
    LineChartView graph;

    @ViewById(R.id.day_wind)
    TextView wind;

    @ViewById(R.id.day_description)
    TextView description;

    @ViewById(R.id.day_humidity)
    TextView humidity;

    @ViewById(R.id.day_pressure)
    TextView pressure;

    List<Reading> days;

    @FragmentArg("countryName")
    String countryName;

    @FragmentArg("countryCode")
    String countryCode;

    WunderClient client;

    /**
     * The dummy content this fragment is presenting.
     */

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayDetailFragment() {
    }

    @AfterInject
    void calledAfterInjection() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL + API_KEY)
                .build();

//        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        client = restAdapter.create(WunderClient.class);
        days = new LinkedList<Reading>();

        attemptAPICall(client);
    }

    @AfterViews
    void calledAfterViewInjection() {
        getActivity().getActionBar().setTitle(countryName);
    }

    Comparator<Reading> comparator = new Comparator<Reading>() {

        @Override
        public int compare(final Reading o1, final Reading o2) {
            // let your comparator look up your car's color in the custom order
            return Integer.valueOf(o1.hour)
                    .compareTo(Integer.valueOf(o2.hour));
        }
    };


    @Background
    void attemptAPICall(WunderClient client) {
//        Weather weather = client.getCityWeather("Dublin, Ireland");
        Hourly forecast = null;

        if (countryName != null)
            forecast = client.getHourlyForecast(countryCode, countryName);

        updateView(forecast);
    }

    @UiThread
    void updateView(Hourly forecast) {
        day_detail.setText(forecast.getHourlyForecast().get(0).getFCTTIME().getWeekdayName());

        for (HourlyForecast dailyForecast : forecast.getHourlyForecast()) {

            String temp = dailyForecast.getTemp().getMetric();

            days.add(new Reading(temp, dailyForecast.getFCTTIME().getHourPadded(), ""));
            setGeneralStats(dailyForecast);
        }

        LineSet lineSet = new LineSet();

        // Style dots
        lineSet.setDots(true);
        lineSet.setDotsColor(getResources().getColor(R.color.line_dots));
        lineSet.setDotsRadius(4);
        lineSet.setDotsStrokeThickness(1);
        lineSet.setDotsStrokeColor(getResources().getColor(R.color.dot_fill));

        // Style line
        lineSet.setLineThickness(5);
        lineSet.setLineColor(getResources().getColor(R.color.line_dash));

        // Style background fill
        lineSet.setFill(true);
        lineSet.setFillColor(getResources().getColor(R.color.line_fill));

        // Style type
//        lineSet.setDashed(true);
        lineSet.setSmooth(true);

        Collections.sort(days, comparator);

        for (Reading day : days) {
            lineSet.addPoint(new Point(day.hour, Integer.valueOf(day.maxTmp)));
        }

        graph.addData(lineSet);

        graph.setGrid(randPaint())
                .setMaxAxisValue(24, 8)
                        .setAxisX(false)
                        //.setVerticalGrid(randPaint())
                .setHorizontalGrid(randPaint())
                        //.setThresholdLine(2, randPaint())
                        //.setLabels(true)
                .animate(randAnimation())
        //.show()
        ;
    }

    private Animation randAnimation() {

        switch (new Random().nextInt(3)) {
            case 0:
                return new Animation()
                        .setEasing(new QuintEaseOut());
//                        .setEndAction(mEndAction);
            case 1:
                return new Animation()
                        .setEasing(new BounceEaseOut());
//                        .setEndAction(mEndAction);
            default:
                return new Animation()
                        .setAnimateInSequence(randBoolean())
                        .setEasing(new ElasticEaseOut());
//                        .setEndAction(mEndAction);
        }
    }

    private static boolean randBoolean() {
        return Math.random() < 0.5;
    }


    private static Paint randPaint() {

        if (randBoolean()) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#b0bec5"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(Tools.fromDpToPx(1));
            if (randBoolean())
                paint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
            return paint;
        }
        return null;
    }

    private void setGeneralStats(HourlyForecast general) {
        description.setText(general.getCondition());
        humidity.setText("Humidity: " + general.getHumidity());
        wind.setText("Wind Speed: " + general.getWspd().getMetric());
        pressure.setText("Feels like: " + general.getFeelslike().getMetric());
    }

    private class Reading {
        String maxTmp;
        String hour;
        String description;

        private Reading(String maxTmp, String hour, String description) {
            this.maxTmp = maxTmp;
            this.hour = hour;
            this.description = description;
        }
    }
}
