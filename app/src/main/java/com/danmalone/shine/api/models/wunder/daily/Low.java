
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;

public class Low {

    @Expose
    private String fahrenheit;
    @Expose
    private String celsius;

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
