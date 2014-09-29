
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;

public class SnowDay {

    @Expose
    private float in;
    @Expose
    private float cm;

    public float getIn() {
        return in;
    }

    public void setIn(float in) {
        this.in = in;
    }

    public float getCm() {
        return cm;
    }

    public void setCm(float cm) {
        this.cm = cm;
    }

}
