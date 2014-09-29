
package com.danmalone.shine.api.models.wunder.hourly;

import com.google.gson.annotations.Expose;

public class Response {

    @Expose
    private String version;
    @Expose
    private String termsofService;
    @Expose
    private Features features;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

}
