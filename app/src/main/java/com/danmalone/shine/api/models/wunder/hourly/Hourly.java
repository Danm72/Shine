
package com.danmalone.shine.api.models.wunder.hourly;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hourly {

    @Expose
    private Response response;
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = new ArrayList<HourlyForecast>();

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

}
