
package com.danmalone.shine.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Wind {

    @Expose
    private Double speed;
    @Expose
    private Double deg;
    @SerializedName("var_beg")
    @Expose
    private Integer varBeg;
    @SerializedName("var_end")
    @Expose
    private Integer varEnd;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Integer getVarBeg() {
        return varBeg;
    }

    public void setVarBeg(Integer varBeg) {
        this.varBeg = varBeg;
    }

    public Integer getVarEnd() {
        return varEnd;
    }

    public void setVarEnd(Integer varEnd) {
        this.varEnd = varEnd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
