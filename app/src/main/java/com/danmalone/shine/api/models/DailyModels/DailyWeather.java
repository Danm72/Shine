
package com.danmalone.shine.api.models.DailyModels;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;

public class DailyWeather {

    @Expose
    private Integer dt;
    @Expose
    private Temp temp;
    @Expose
    private Double pressure;
    @Expose
    private Integer humidity;
    @Expose
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    @Expose
    private Double speed;
    @Expose
    private Integer deg;
    @Expose
    private Integer clouds;
    @Expose
    private Double rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

}
