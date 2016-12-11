package com.mmarvick.weatherapp.ui;

/**
 * Created by michael on 12/10/16.
 */

public interface WeatherView {
    void showContent();
    void showLoading();
    void showError(String title, String content, String buttonText);
    void setLoadingText(String loadingText);
    void setLocation(String location);
    void setDegrees(int degrees);
    void setFahrenheit();
    void setCelsius();
}
