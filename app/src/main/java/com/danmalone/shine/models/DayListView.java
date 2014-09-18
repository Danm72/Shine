package com.danmalone.shine.models;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danmalone.shine.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by danmalone on 12/09/2014.
 */

@EViewGroup(R.layout.day_list_item)
public class DayListView extends LinearLayout {
    Context context;

    @ViewById
    TextView daylist_weather_text;

    @ViewById
    TextView daylist_weather_degree;

    @ViewById
    ImageView daylist_weather_icon;

    public DayListView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(DayListModel model) {
        daylist_weather_text.setText(model.day);
        daylist_weather_degree.setText(model.daylist_weather_degree);
        daylist_weather_icon.setImageDrawable(context.getResources().getDrawable(model.imgResource));
//        Picasso.with(context).load(model.imgResource).into(daylist_weather_icon);
    }

}


