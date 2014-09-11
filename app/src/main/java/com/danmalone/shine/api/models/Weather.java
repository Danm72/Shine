
package com.danmalone.shine.api.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Weather {

    @Expose
    private Coord coord;
    @Expose
    private Sys sys;
    @Expose
    @SerializedName("weather")
    private List<Description> descriptions = new ArrayList<Description>();
    @Expose
    private String base;
    @Expose
    private Temp main;
    @Expose
    private Wind wind;
    @Expose
    private Clouds clouds;
    @Expose
    private Integer dt;
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Description> getDescription() {
        return descriptions;
    }

    public void setDescription(List<Description> description) {
        this.descriptions = description;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Temp getMain1() {
        return main;
    }

    public void setMain1(Temp temp) {
        this.main = temp;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
