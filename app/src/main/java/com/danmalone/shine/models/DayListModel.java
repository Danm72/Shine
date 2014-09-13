package com.danmalone.shine.models;

/**
 * Created by danmalone on 12/09/2014.
 */
public class DayListModel {

    public String day;
    public String daylist_weather_degree;
    public int imgResource;

    public DayListModel(String day, String daylist_weather_degree, int imgResource) {
        this.day = day;
        this.daylist_weather_degree = daylist_weather_degree;
        this.imgResource = imgResource;
    }

}
