package com.mmarvick.weatherapp.dagger;

import android.app.Application;

import com.mmarvick.weatherapp.api.JsonAdapterFactory;
import com.mmarvick.weatherapp.api.WeatherService;
import com.mmarvick.weatherapp.model.LocationModel;
import com.mmarvick.weatherapp.presenter.WeatherPresenter;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by michael on 12/10/16.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(JsonAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }
}
