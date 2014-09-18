
package com.danmalone.shine.api.models.wunder.daily;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Simpleforecast {

    @Expose
    private List<Forecastday_> forecastday = new ArrayList<Forecastday_>();

    public List<Forecastday_> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday_> forecastday) {
        this.forecastday = forecastday;
    }

}
