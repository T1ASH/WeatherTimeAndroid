package com.toddashmore.weathertime.services;

import com.toddashmore.weathertime.models.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This is an interface necessary for Retrofit to properly work with the Darksky API.
 */
public interface WeatherService {
    @GET("{latitude},{longitude}")
    Call<Forecast> getWeather(
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}