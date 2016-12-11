package com.mmarvick.weatherapp.dagger;

import com.mmarvick.weatherapp.ui.LocationSearchActivity;
import com.mmarvick.weatherapp.ui.WeatherActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by michael on 12/10/16.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(LocationSearchActivity activity);
    void inject(WeatherActivity activity);
}
