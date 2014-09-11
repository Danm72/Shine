package com.danmalone.shine;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.danmalone.shine.api.clients.OWMClient;
import com.danmalone.shine.api.models.Weather;
import com.danmalone.shine.dummy.DummyContent;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;

import java.util.LinkedList;
import java.util.List;

import retrofit.RestAdapter;

import static com.danmalone.shine.api.clients.OWMClient.*;

/**
 * A fragment representing a single Day detail screen.
 * This fragment is either contained in a {@link DayListActivity}
 * in two-pane mode (on tablets) or a {@link DayDetailActivity}
 * on handsets.
 */
@EFragment
public class DayDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        // Create an instance of our GitHub API interface.
        client = restAdapter.create(OWMClient.class);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Background
    void attemptAPICall(OWMClient client) {
        List<String> list = new LinkedList<String>();
        list.add("Dublin");
        list.add("Ireland");
        Weather contributors = client.getCity("Dublin, Ireland");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_day_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.day_detail)).setText(mItem.content);
        }

        attemptAPICall(client);


        return rootView;
    }
}
