package com.danmalone.shine;

import android.app.Fragment;
import android.graphics.Color;
import android.widget.TextView;

import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.DailyForecast;
import com.danmalone.shine.api.models.ForecastModels.DetailedForecast;
import com.danmalone.shine.api.models.ForecastModels.Forecast;
import com.danmalone.shine.dummy.DummyContent;
import com.danmalone.shine.models.DayListModel;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    LineGraph linegraph;

    Map<String,DayListModel> days;

    OWMClient client;

    @FragmentArg("location")
    String location;

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
        days = new HashMap<String, DayListModel>();

        attemptAPICall(client);
    }

    @AfterViews
    void calledAfterViewInjection() {

        Line l = new Line();
        LinePoint p = new LinePoint();
        p.setX(0);
        p.setY(5);
        l.addPoint(p);
        p = new LinePoint();
        p.setX(8);
        p.setY(8);
        l.addPoint(p);
        p = new LinePoint();
        p.setX(10);
        p.setY(4);
        l.addPoint(p);
        l.setColor(Color.parseColor("#FFBB33"));

        linegraph.addLine(l);
        linegraph.setRangeY(0, 10);
        linegraph.setLineToFill(0);

        linegraph.setOnPointClickedListener(new LineGraph.OnPointClickedListener() {

            @Override
            public void onClick(int lineIndex, int pointIndex) {
                // TODO Auto-generated method stub

            }

        });
    }

    @Background
    void attemptAPICall(OWMClient client) {
//        Weather weather = client.getCityWeather("Dublin, Ireland");
        Forecast forecast = null;

        if(location!=null)
            forecast = client.forcastWeatherAtCity(location);

        updateView(forecast);
    }

    @UiThread
    void updateView(Forecast forecast) {
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

            int drawable = decideOnIcon(dailyForecast);
            days.put(dayOfMonthStr, new DayListModel(dayOfMonthStr, temp + "°", drawable));
        }
    }

    int decideOnIcon(DetailedForecast forecast) {
        double temp = forecast.getMain().getTemp();
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
