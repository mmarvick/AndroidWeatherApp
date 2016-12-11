package com.mmarvick.weatherapp.model;

import com.mmarvick.weatherapp.data.LocationWeather;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by michael on 12/10/16.
 */

@Singleton
public class WeatherModel {
    private int locationId;
    private LocationWeather locationWeather;

    @Inject
    public WeatherModel() {
        // Pass
    }

    public void setLocationWeather(int locationId, LocationWeather locationWeather) {
        this.locationId = locationId;
        this.locationWeather = locationWeather;
    }

    public LocationWeather getWeatherForLocation(int locationId) {
        if (this.locationId == locationId) {
            return locationWeather;
        } else {
            return null;
        }
    }
}
