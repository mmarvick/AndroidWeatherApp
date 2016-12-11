package com.mmarvick.weatherapp.ui;

/**
 * Created by michael on 12/10/16.
 */

public interface LocationSearchView {
    void enableLocationInputEdit();
    void disableLocationInputEdit();
    void showSearchButton();
    void hideSearchButton();
    void showLoading();
    void hideLoading();
    void showError(String errorText);
    void hideError();
    void navigateToWeather();
}
