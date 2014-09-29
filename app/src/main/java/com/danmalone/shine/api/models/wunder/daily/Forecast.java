
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("txt_forecast")
    @Expose
    private TxtForecast txtForecast;
    @Expose
    private Simpleforecast simpleforecast;

    public TxtForecast getTxtForecast() {
        return txtForecast;
    }

    public void setTxtForecast(TxtForecast txtForecast) {
        this.txtForecast = txtForecast;
    }

    public Simpleforecast getSimpleforecast() {
        return simpleforecast;
    }

    public void setSimpleforecast(Simpleforecast simpleforecast) {
        this.simpleforecast = simpleforecast;
    }

}
