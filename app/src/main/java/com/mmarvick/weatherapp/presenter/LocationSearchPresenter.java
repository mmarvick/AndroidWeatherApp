package com.mmarvick.weatherapp.presenter;

import com.mmarvick.weatherapp.api.WeatherService;
import com.mmarvick.weatherapp.data.LocationMetadata;
import com.mmarvick.weatherapp.model.LocationModel;
import com.mmarvick.weatherapp.ui.LocationSearchView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by michael on 12/10/16.
 */

@Singleton
public class LocationSearchPresenter {
    private LocationSearchView view;

    private final LocationModel locationModel;
    private final WeatherService weatherService;

    private boolean shouldNavigateToWeather;
    private boolean isSearching;
    private String error;

    @Inject
    public LocationSearchPresenter(LocationModel locationModel, WeatherService weatherService) {
        this.locationModel = locationModel;
        this.weatherService = weatherService;
        resetState();
    }

    public void onAttach(LocationSearchView view) {
        this.view = view;
        onPresent();
    }

    public void onPresent() {
        if (view == null) {
            return;
        }

        if (shouldNavigateToWeather) {
            view.navigateToWeather();
        }

        if (isSearching) {
            view.disableLocationInputEdit();
            view.showLoading();
            view.hideSearchButton();
            view.hideError();
        } else {
            view.enableLocationInputEdit();
            view.hideLoading();
            view.showSearchButton();
            if (error != null) {
                view.showError(error);
            } else {
                view.hideError();
            }
        }
    }

    public void onSearch(String query) {
        isSearching = true;
        onPresent();

        weatherService.searchLocations(query).enqueue(new Callback<List<LocationMetadata>>() {
            @Override
            public void onResponse(Call<List<LocationMetadata>> call,
                    Response<List<LocationMetadata>> response) {
                if (response.body().isEmpty()) {
                    handleError("Couldn't find that location");
                } else {
                    locationModel.setLocation(response.body().get(0));
                    navigateToWeather();
                }
            }

            @Override
            public void onFailure(Call<List<LocationMetadata>> call, Throwable t) {
                handleError(t.getMessage());
            }
        });
    }

    private void handleError(String error) {
        this.error = error;
        this.isSearching = false;
        onPresent();
    }

    private void navigateToWeather() {
        if (view == null) {
            shouldNavigateToWeather = true;
        }

        view.navigateToWeather();
        resetState();
    }

    private void resetState() {
        shouldNavigateToWeather = false;
        isSearching = false;
        error = null;
    }
}
