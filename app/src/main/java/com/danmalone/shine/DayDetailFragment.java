package com.danmalone.shine;

import android.app.Fragment;
import android.widget.TextView;

import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.Forecast;
import com.danmalone.shine.dummy.DummyContent;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

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

    OWMClient client;

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
    }

    @AfterViews
    void calledAfterViewInjection() {
        attemptAPICall(client);
    }

    @Background
    void attemptAPICall(OWMClient client) {
//        Weather weather = client.getCityWeather("Dublin, Ireland");
        Forecast dublin = client.forcastWeatherAtCity("Dublin, IE");
        Forecast london = client.forcastWeatherAtCity("London, GB");
        Forecast newyork = client.forcastWeatherAtCity("New York, US");

        updateView(newyork);
    }

    @UiThread
    void updateView(Forecast forecast) {
        day_detail.setText(forecast.getCity().getName() + ", Temp: " + forecast.getList().get(0).getMain().getTemp());
    }
}
