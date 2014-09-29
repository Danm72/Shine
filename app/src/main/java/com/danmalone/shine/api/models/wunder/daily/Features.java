
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private int forecast10day;

    public int getForecast10day() {
        return forecast10day;
    }

    public void setForecast10day(int forecast10day) {
        this.forecast10day = forecast10day;
    }

}
