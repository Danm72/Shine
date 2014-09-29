
package com.danmalone.shine.api.models.wunder.daily;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class TxtForecast {

    @Expose
    private String date;
    @Expose
    private List<Forecastday> forecastday = new ArrayList<Forecastday>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

}
