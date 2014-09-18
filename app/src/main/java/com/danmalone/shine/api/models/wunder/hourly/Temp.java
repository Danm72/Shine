
package com.danmalone.shine.api.models.wunder.hourly;

import com.google.gson.annotations.Expose;

public class Temp {

    @Expose
    private String english;
    @Expose
    private String metric;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}
