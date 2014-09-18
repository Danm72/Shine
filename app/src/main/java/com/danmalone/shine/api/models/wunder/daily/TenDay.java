
package com.danmalone.shine.api.models.wunder.daily;


import com.danmalone.shine.api.models.wunder.*;
import com.google.gson.annotations.Expose;

public class TenDay {

    @Expose
    private Response response;
    @Expose
    private Forecast forecast;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
