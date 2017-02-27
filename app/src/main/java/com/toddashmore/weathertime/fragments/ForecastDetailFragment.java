package com.toddashmore.weathertime.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;

import java.util.List;

import com.toddashmore.weathertime.utilities.AndroidHelper;
import com.toddashmore.weathertime.utilities.DateTimeHelper;
import edu.unmc.weathertime.R;
import com.toddashmore.weathertime.models.Datum;

/**
 * When a user selects a day from the RecyclerView weekly forecast on the ForecastMasterFragment, this Fragment is displayed.
 * The index (int selectedDay) is passed into the newInstance and then used to populate all data.
 */
public class ForecastDetailFragment extends Fragment {
    View view;

    /**
     * Creates a new instance of ForecastDetailFragment. This is primarily used so that we can pass the index (int selectedDay).
     * @param forecast All forecast data. This is narrowed down by using selectedDay.
     * @param selectedDay Used to show the day selected by the user.
     * @return ForecastDetailFragment
     */
    public static ForecastDetailFragment newInstance(List<Datum> forecast, int selectedDay) {

        // Create a bundle with all weather data so that we can access it when the view is being created below.
        Bundle args = createBundle(forecast, selectedDay);

        // Create the new ForecastDetailFragment, set it's argument and then return it.
        ForecastDetailFragment fragment = new ForecastDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Setup the view so that we can access it's components.
        if(view == null) this.view = inflater.inflate(R.layout.fragment_forecast_detail, container, false);

        // Populate all the text views on the fragment with the correct data.
        populateWeatherData();

        return view;
    }

    /**
     * Pulls all arguments and then pouplates the text views with the correct data.
     */
    private void populateWeatherData() {

        // Get all of our arguments that were populated earlier.
        Bundle args = getArguments();

        /**
         * Setup all of our text views.
         */
        TextView detailDate         = (TextView) view.findViewById(R.id.text_detail_date);
        TextView condition          = (TextView) view.findViewById(R.id.text_detail_condition);
        WeatherIconView weatherIcon = (WeatherIconView) view.findViewById(R.id.weather_icon_detail);
        TextView lowTemp            = (TextView) view.findViewById(R.id.text_detail_low);
        TextView highTemp           = (TextView) view.findViewById(R.id.text_detail_high);

        /**
         * Format all of our data correctly.
         */
        String dateString           = DateTimeHelper.convertEpochToString(args.getInt("time"), "EEEE", "GMT-6:00");
        String conditionString      = args.getString("condition");
        String weatherIconResource  = getString(AndroidHelper.getStringIdentifier(getActivity(), args.getString("icon")));
        long lowTempLong            = Math.round(Double.valueOf(args.getString("low")));
        long highTempLong           = Math.round(Double.valueOf(args.getString("high")));


        /**
         * Populate data from the bundle into the text views.
         */
        detailDate.setText(dateString);
        condition.setText(conditionString);
        weatherIcon.setIconResource(weatherIconResource);
        lowTemp.setText(getString(R.string.weather_temperature, lowTempLong));
        highTemp.setText(getString(R.string.weather_temperature, highTempLong));
    }

    /**
     * A helper to create our bundle that is used within onCreateView.
     * @param forecast
     * @param selectedDay
     * @return Bundle
     */
    private static Bundle createBundle(List<Datum> forecast, int selectedDay) {
        // Create a bundle so that we can access it when the view is being created below.
        Bundle bundle = new Bundle();

        // Filter out the selected day from the forecast.
        Datum dailyForecast = forecast.get(selectedDay);

        /**
         * Build out our bundle with weather data.
         */
        bundle.putInt("time", dailyForecast.getTime());
        bundle.putString("condition", dailyForecast.getSummary());
        bundle.putString("icon", "wi_forecast_io_" + dailyForecast.getIcon().replace("-", "_"));
        bundle.putString("low", String.valueOf(dailyForecast.getTemperatureMin()));
        bundle.putString("high", String.valueOf(dailyForecast.getTemperatureMax()));

        return bundle;
    }
}
