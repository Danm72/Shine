package com.danmalone.shine.models;

import android.content.Context;
import android.location.Address;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danmalone.shine.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by danmalone on 12/09/2014.
 */

@EViewGroup(R.layout.search_list_item)
public class SearchListView extends LinearLayout {
    Context context;

    @ViewById(R.id.search_country_text)
    TextView country;

    @ViewById(R.id.search_country_code)
    TextView code;

    public SearchListView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(Address model) {
        country.setText(model.getCountryName());
        code.setText(model.getCountryCode());
    }

}


