package com.toddashmore.weathertime.models;

import android.app.Activity;
import android.location.Location;

import static com.toddashmore.weathertime.utilities.SharedPreferencesHelper.getStringFromSharedPrefs;

public class WeatherLocation {
    String name;
    String latitude;
    String longitude;

    public WeatherLocation(Activity activity) {
        setName(getStringFromSharedPrefs("city", activity));
        setLatitude(getStringFromSharedPrefs("latitude", activity));
        setLongitude(getStringFromSharedPrefs("longitude", activity));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location getLatitudeLongitude() {
        Location location = new Location("");

        location.setLatitude(Double.parseDouble(getLatitude()));
        location.setLongitude(Double.parseDouble(getLongitude()));

        return location;
    }

}