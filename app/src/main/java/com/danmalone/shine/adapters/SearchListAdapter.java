package com.danmalone.shine.adapters;

import android.content.Context;
import android.location.Address;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.danmalone.shine.models.SearchListView;
import com.danmalone.shine.models.SearchListView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by danmalone on 12/09/2014.
 */

@EBean
public class SearchListAdapter extends BaseAdapter {
    List<Address> addresses;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        addresses = new LinkedList<Address>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SearchListView addressItemView;
        if (convertView == null) {
            addressItemView = SearchListView_.build(context);
        } else {
            addressItemView = (SearchListView_) convertView;
        }

        addressItemView.bind(getItem(position));

        return addressItemView;
    }

    public void add(Address object) {
        addresses.add(object);
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Address getItem(int i) {
        return addresses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
