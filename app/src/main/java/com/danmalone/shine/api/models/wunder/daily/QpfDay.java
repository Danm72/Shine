
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;

public class QpfDay {

    @Expose
    private float in;
    @Expose
    private int mm;

    public float getIn() {
        return in;
    }

    public void setIn(float in) {
        this.in = in;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

}
