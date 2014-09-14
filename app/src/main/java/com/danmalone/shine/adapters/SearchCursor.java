package com.danmalone.shine.adapters;

import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.danmalone.shine.R;
import com.danmalone.shine.dao.AddressDAO;
import com.danmalone.shine.dao.DatabaseHelper;

import java.util.List;

/**
 * Created by danmalone on 14/09/2014.
 */
public class SearchCursor extends CursorAdapter {

    private DatabaseHelper dbhelper;
    private List<Address> items;

    private TextView text;
    private TextView code;
    Address addy;

    Callbacks mCallbacks;

    public SearchCursor(Context context, Cursor cursor, List items, DatabaseHelper dbhelper) {
        super(context, cursor, false);
        this.dbhelper = dbhelper;
        this.items = items;
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {

        addy = items.get(cursor.getPosition());
        // Show list item data from cursor
        if (addy.getFeatureName() != null)
            text.setText(addy.getFeatureName() + ", " + addy.getCountryName());
        code.setText(addy.getCountryCode());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address addy = items.get(cursor.getPosition());
                mCallbacks.onItemSelected(addy);
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.search_list_item, parent, false);

        text = (TextView) view.findViewById(R.id.search_country_text);
        code = (TextView) view.findViewById(R.id.search_country_code);

        return view;

    }

    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(Address location);
    }

}