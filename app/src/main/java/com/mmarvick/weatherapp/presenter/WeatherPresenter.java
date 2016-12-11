package com.mmarvick.weatherapp.presenter;

import com.mmarvick.weatherapp.model.LocationModel;
import com.mmarvick.weatherapp.model.WeatherModel;
import com.mmarvick.weatherapp.ui.WeatherView;
import com.mmarvick.weatherapp.api.WeatherService;
import com.mmarvick.weatherapp.data.LocationWeather;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by michael on 12/10/16.
 */
@Singleton
public class WeatherPresenter {

    private WeatherView view;
    private final LocationModel locationModel;
    private final WeatherModel weatherModel;
    private final WeatherService weatherService;

    private boolean isCelsius;

    @Inject
    public WeatherPresenter(LocationModel locationModel, WeatherModel weatherModel,
            WeatherService weatherService) {
        this.locationModel = locationModel;
        this.weatherModel = weatherModel;
        this.weatherService = weatherService;
    }

    public void onAttach(WeatherView view) {
        this.view = view;
        fetchLocation(locationModel.location().woeid());
        onPresent();
    }

    public void onRefresh() {
        fetchLocation(locationModel.location().woeid());
        showLoading();
    }

    public void onSwitchDegreeUnit() {
        this.isCelsius = !this.isCelsius;
        onPresent();
    }

    void onPresent() {
        if (view == null) {
            return;
        }

        LocationWeather locationWeather = getLocationWeather();
        if (locationWeather == null) {
            showLoading();
            return;
        }

        view.showContent();
        view.setLocation(locationWeather.title());
        if (isCelsius) {
            view.setDegrees(getDegreesCelsius());
            view.setCelsius();
        } else {
            view.setDegrees(getDegreesFahrenheit());
            view.setFahrenheit();
        }
    }

    private void showLoading() {
        String locationName = locationModel.location().title();
        view.setLoadingText(String.format(Locale.US,
                "Loading weather for %s\n\nJust one moment", locationName));
        view.showLoading();
    }

    private LocationWeather getLocationWeather() {
        assert locationModel.hasLocation();
        int locationId = locationModel.location().woeid();
        return weatherModel.getWeatherForLocation(locationId);
    }

    private void fetchLocation(final int id) {
        weatherService.getLocation(id).enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                WeatherPresenter.this.weatherModel.setLocationWeather(id, response.body());
                onPresent();
            }

            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                view.showError("Error", t.getMessage(), "Refresh");
            }
        });
    }

    private int getDegreesCelsius() {
        float degC = getLocationWeather().consolidatedWeather().get(0).temp();
        return Math.round(degC);
    }

    private int getDegreesFahrenheit() {
        float degC = getLocationWeather().consolidatedWeather().get(0).temp();
        return Math.round(1.8f * degC + 32f);
    }
}
