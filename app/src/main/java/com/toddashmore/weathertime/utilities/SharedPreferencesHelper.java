package com.toddashmore.weathertime.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

public class SharedPreferencesHelper {

    /**
     * A helper function that sets a new string to SharedPreferences by using the key and string provided.
     * @param key
     * @param string
     */
    public static void setStringToSharedPrefs(String key, String string, Activity activity) {
        SharedPreferences sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);
        sharedPrefs
                .edit()
                .putString(key, string)
                .apply();
    }

    /**
     * A helper function that gets a string from SharedPreferences by using the key provided.
     * @param key
     * @return String
     */
    public static String getStringFromSharedPrefs(String key, Activity activity) {
        SharedPreferences sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);

        return sharedPrefs.getString(key, null);
    }

    public static void CreateCityInSharedPrefs(Activity activity, String providedLocation) {
        try {
            Geocoder geocoder = new Geocoder(activity);

            Address location = geocoder.getFromLocationName(providedLocation, 1).get(0);

            // Save the new city value to SharedPreferences.
            setStringToSharedPrefs("city", location.getAddressLine(0), activity);


            // Set the latitude and longitude in shared preferences.
            setStringToSharedPrefs("latitude", String.valueOf(location.getLatitude()), activity);
            setStringToSharedPrefs("longitude", String.valueOf(location.getLongitude()), activity);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
