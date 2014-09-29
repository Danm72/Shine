
package com.danmalone.shine.api.models.wunder.daily;

import com.google.gson.annotations.Expose;

public class Maxwind {

    @Expose
    private int mph;
    @Expose
    private int kph;
    @Expose
    private String dir;
    @Expose
    private int degrees;

    public int getMph() {
        return mph;
    }

    public void setMph(int mph) {
        this.mph = mph;
    }

    public int getKph() {
        return kph;
    }

    public void setKph(int kph) {
        this.kph = kph;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

}
