
package com.danmalone.shine.api.models.ForecastModels;

import com.google.gson.annotations.Expose;


public class Clouds {

    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}