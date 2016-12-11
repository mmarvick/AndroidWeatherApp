package com.mmarvick.weatherapp;

import android.app.Application;

import com.mmarvick.weatherapp.dagger.AppComponent;
import com.mmarvick.weatherapp.dagger.AppModule;
import com.mmarvick.weatherapp.dagger.DaggerAppComponent;

/**
 * Created by michael on 12/10/16.
 */

public class WeatherApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
