
package com.danmalone.shine.api.models.wunder.hourly;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyForecast {

    @SerializedName("FCTTIME")
    @Expose
    private FCTTIME fCTTIME;
    @Expose
    private Temp temp;
    @Expose
    private Dewpoint dewpoint;
    @Expose
    private String condition;
    @Expose
    private String icon;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @Expose
    private String fctcode;
    @Expose
    private String sky;
    @Expose
    private Wspd wspd;
    @Expose
    private Wdir wdir;
    @Expose
    private String wx;
    @Expose
    private String uvi;
    @Expose
    private String humidity;
    @Expose
    private Windchill windchill;
    @Expose
    private Heatindex heatindex;
    @Expose
    private Feelslike feelslike;
    @Expose
    private Qpf qpf;
    @Expose
    private Snow snow;
    @Expose
    private String pop;
    @Expose
    private Mslp mslp;

    public FCTTIME getFCTTIME() {
        return fCTTIME;
    }

    public void setFCTTIME(FCTTIME fCTTIME) {
        this.fCTTIME = fCTTIME;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(Dewpoint dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getFctcode() {
        return fctcode;
    }

    public void setFctcode(String fctcode) {
        this.fctcode = fctcode;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public Wspd getWspd() {
        return wspd;
    }

    public void setWspd(Wspd wspd) {
        this.wspd = wspd;
    }

    public Wdir getWdir() {
        return wdir;
    }

    public void setWdir(Wdir wdir) {
        this.wdir = wdir;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getUvi() {
        return uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Windchill getWindchill() {
        return windchill;
    }

    public void setWindchill(Windchill windchill) {
        this.windchill = windchill;
    }

    public Heatindex getHeatindex() {
        return heatindex;
    }

    public void setHeatindex(Heatindex heatindex) {
        this.heatindex = heatindex;
    }

    public Feelslike getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(Feelslike feelslike) {
        this.feelslike = feelslike;
    }

    public Qpf getQpf() {
        return qpf;
    }

    public void setQpf(Qpf qpf) {
        this.qpf = qpf;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public Mslp getMslp() {
        return mslp;
    }

    public void setMslp(Mslp mslp) {
        this.mslp = mslp;
    }

}
