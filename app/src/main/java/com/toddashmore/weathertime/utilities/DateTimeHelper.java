package com.toddashmore.weathertime.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeHelper {


    /**
     * Converts a provided epoch to the format that is provided.
     * @param epoch The epoch that needs to be converted.
     * @param format The format to use.
     * @param timezone The timezone of the user.
     * @return
     */
    public static String convertEpochToString(Integer epoch, String format, String timezone) {
        // Convert the epoch to a long and then create a new date with it.
        Date date = new Date(epoch.longValue() * 1000);

        // Create a new calendar using the provided timezone.
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));

        // Add the new date to the calendar.
        calendar.setTime(date);

        // Return a formatted date string using SimpleDateFormat and the provided format.
        return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());

    }
}
