
package com.danmalone.shine.api.models.ForecastModels;

import com.google.gson.annotations.Expose;


public class Sys_ {

    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
