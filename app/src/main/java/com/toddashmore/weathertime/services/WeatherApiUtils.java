package com.toddashmore.weathertime.services;

import android.location.Location;
import android.util.Log;

import java.util.List;

import com.toddashmore.weathertime.adapters.ForecastMasterAdapter;
import com.toddashmore.weathertime.fragments.ForecastMasterFragment;
import com.toddashmore.weathertime.models.Datum;
import com.toddashmore.weathertime.models.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherApiUtils {
    private static final String BASE_URL = "https://api.darksky.net/forecast/";

    /**
     * Uses retrofit to call the Darksky api using the provided api key.
     * @param apiKey Needed to access the Darksky api.
     * @return WeatherService
     */
    private static WeatherService getWeatherService(String apiKey) {
        return RetrofitClient.getClient(BASE_URL+apiKey).create(WeatherService.class);
    }

    /**
     * Pulls weather data from the Darksky API using the provided location. On success it updates the adapter and forecastMasterFragment.
     * @param location Used to pull the weather data for this particular location.
     * @param adapter Used to update the adapter of the RecyclerView for forecastMasterFragment.
     * @param apiKey Needed to access the Darksky api.
     * @param forecastMasterFragment Used to update the current conditions on this fragment.
     */
    public static void getWeatherData(Location location, final ForecastMasterAdapter adapter, String apiKey, final ForecastMasterFragment forecastMasterFragment) {
        WeatherService api = WeatherApiUtils.getWeatherService(apiKey);

        Log.i("API", api.getWeather(location.getLatitude(), location.getLongitude()).request().url().toString());

        api.getWeather(location.getLatitude(), location.getLongitude()).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    List<Datum> dailyData = response.body().getDaily().getData();

                    // Update the forecast data, but return a new list that does not have today in it.
                    adapter.updateForecastData(dailyData.subList(1, dailyData.size()));

                    // Update the current conditions views.
                    forecastMasterFragment.updateCurrentConditions(response.body());
                } else {
                    Log.e("rest error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d("Weather API", "error loading from API");
                Log.d("Weather API", t.getMessage());
            }
        });
    }
}