
package com.toddashmore.weathertime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("summary")
    @Expose
    private String summary;

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
