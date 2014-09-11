package com.danmalone.shine.api.models;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;

public class CityResponse {

    @Expose
    private String message;
    @Expose
    private String cod;
    @Expose
    private String type;
    @Expose
    private String calctime;
    @Expose
    private String units;
    @Expose
    private Integer count;
    @Expose
    private java.util.List<City> list = new ArrayList<City>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCalctime() {
        return calctime;
    }

    public void setCalctime(String calctime) {
        this.calctime = calctime;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<City> getList() {
        return list;
    }

    public void setList(java.util.List<City> list) {
        this.list = list;
    }

}