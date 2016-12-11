package com.mmarvick.weatherapp.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by michael on 12/10/16.
 */

@AutoValue
public abstract class ConsolidatedWeather {
    @Json(name="the_temp") public abstract float temp();

    public static JsonAdapter<ConsolidatedWeather> jsonAdapter(Moshi moshi) {
        return new AutoValue_ConsolidatedWeather.MoshiJsonAdapter(moshi);
    }
}
