package com.danmalone.shine.adapters;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.danmalone.shine.DayListFragment_;

import java.util.ArrayList;

/**
 * Created by danmalone on 12/09/2014.
 */
public class TabsAdapter extends FragmentStatePagerAdapter
        implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    private final Context mContext;
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

    public static final class TabInfo {
        private final Class<?> clss;
        private final Bundle args;
        public final String name;
        public final String code;

        TabInfo(Class<?> _class, String _name, String code, Bundle _args) {
            clss = _class;
            this.code = code;
            args = _args;
            name = _name;
        }
    }

    public TabsAdapter(FragmentActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void addTab(ActionBar.Tab tab, Class<?> clss, String name,String code, Bundle args) {
        TabInfo info = new TabInfo(clss, name,code, args);
        tab.setTag(info);
        tab.setTabListener(this);
        mTabs.add(info);
        mActionBar.addTab(tab);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);

        return DayListFragment_.builder().countryCode(info.name).countryName(info.code)
                .build();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public TabInfo currentTab(int position) {
        return mTabs.get(position);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Object tag = tab.getTag();
        for (int i = 0; i < mTabs.size(); i++) {
            TabInfo tabInfo = mTabs.get(i);
            if (tabInfo == tag) {
                mViewPager.setCurrentItem(i);
            }
        }
    }

    public void removeItem(int position) {
        mTabs.remove(position);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}