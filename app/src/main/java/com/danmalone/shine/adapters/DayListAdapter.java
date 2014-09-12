package com.danmalone.shine.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.danmalone.shine.models.DayListView_;
import com.danmalone.shine.models.DayListModel;
import com.danmalone.shine.models.DayListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by danmalone on 12/09/2014.
 */

@EBean
public class DayListAdapter extends BaseAdapter {
    List<DayListModel> days;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        days = new LinkedList<DayListModel>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DayListView personItemView;
        if (convertView == null) {
            personItemView = DayListView_.build(context);
        } else {
            personItemView = (DayListView_) convertView;
        }

        personItemView.bind(getItem(position));

        return personItemView;
    }

    public void add(DayListModel object) {
        days.add(object);
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public DayListModel getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
