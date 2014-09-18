
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @Expose
    private String epoch;
    @Expose
    private String pretty;
    @Expose
    private int day;
    @Expose
    private int month;
    @Expose
    private int year;
    @Expose
    private int yday;
    @Expose
    private int hour;
    @Expose
    private String min;
    @Expose
    private int sec;
    @Expose
    private String isdst;
    @Expose
    private String monthname;
    @SerializedName("monthname_short")
    @Expose
    private String monthnameShort;
    @SerializedName("weekday_short")
    @Expose
    private String weekdayShort;
    @Expose
    private String weekday;
    @Expose
    private String ampm;
    @SerializedName("tz_short")
    @Expose
    private String tzShort;
    @SerializedName("tz_long")
    @Expose
    private String tzLong;

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYday() {
        return yday;
    }

    public void setYday(int yday) {
        this.yday = yday;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String getIsdst() {
        return isdst;
    }

    public void setIsdst(String isdst) {
        this.isdst = isdst;
    }

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    public String getMonthnameShort() {
        return monthnameShort;
    }

    public void setMonthnameShort(String monthnameShort) {
        this.monthnameShort = monthnameShort;
    }

    public String getWeekdayShort() {
        return weekdayShort;
    }

    public void setWeekdayShort(String weekdayShort) {
        this.weekdayShort = weekdayShort;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTzShort() {
        return tzShort;
    }

    public void setTzShort(String tzShort) {
        this.tzShort = tzShort;
    }

    public String getTzLong() {
        return tzLong;
    }

    public void setTzLong(String tzLong) {
        this.tzLong = tzLong;
    }

}
