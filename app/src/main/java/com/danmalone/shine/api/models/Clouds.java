
package com.danmalone.shine.api.models;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Clouds {

    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
