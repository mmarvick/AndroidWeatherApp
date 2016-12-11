package com.mmarvick.weatherapp.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * Created by michael on 12/10/16.
 */

@AutoValue
public abstract class LocationWeather {
    public abstract String title();
    @Json(name = "consolidated_weather") public abstract List<ConsolidatedWeather> consolidatedWeather();

    public static JsonAdapter<LocationWeather> jsonAdapter(Moshi moshi) {
        return new AutoValue_LocationWeather.MoshiJsonAdapter(moshi);
    }
}

