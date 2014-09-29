
package com.danmalone.shine.api.models.wunder.hourly;

import com.google.gson.annotations.Expose;

public class Wdir {

    @Expose
    private String dir;
    @Expose
    private String degrees;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

}
