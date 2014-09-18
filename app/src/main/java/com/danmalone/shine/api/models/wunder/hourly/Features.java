
package com.danmalone.shine.api.models.wunder.hourly;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private int hourly;

    public int getHourly() {
        return hourly;
    }

    public void setHourly(int hourly) {
        this.hourly = hourly;
    }

}
