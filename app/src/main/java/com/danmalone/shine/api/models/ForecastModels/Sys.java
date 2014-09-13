
package com.danmalone.shine.api.models.ForecastModels;

import com.google.gson.annotations.Expose;


public class Sys {

    @Expose
    private Integer population;

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}
