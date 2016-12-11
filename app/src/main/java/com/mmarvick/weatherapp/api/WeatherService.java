package com.mmarvick.weatherapp.api;

import com.mmarvick.weatherapp.data.LocationMetadata;
import com.mmarvick.weatherapp.data.LocationWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by michael on 12/10/16.
 */

public interface WeatherService {
    @GET("location/{id}")
    Call<LocationWeather> getLocation(@Path("id") int id);

    @GET("location/search")
    Call<List<LocationMetadata>> searchLocations(@Query("query") String query);
}
