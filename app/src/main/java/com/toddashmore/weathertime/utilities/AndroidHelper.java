package com.toddashmore.weathertime.utilities;

import android.content.Context;

public class AndroidHelper {

    /**
     * This allows you to pull a string identifier dynamically by passing in a string.
     * @param context current context.
     * @param name target of the string.
     * @return int
     *
     * example:
     * String iconString = "wi_forecast_io_" + day.getIcon().replace("-", "_");
     * String weatherIconResource = context.getString(getStringIdentifier(context, iconString));
     */
    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }
}
