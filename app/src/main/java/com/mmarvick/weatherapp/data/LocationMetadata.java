package com.mmarvick.weatherapp.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by michael on 12/10/16.
 */

@AutoValue
public abstract class LocationMetadata {
    public abstract String title();
    public abstract int woeid();
    public abstract String latt_long();

    public static JsonAdapter<LocationMetadata> jsonAdapter(Moshi moshi) {
        return new AutoValue_LocationMetadata.MoshiJsonAdapter(moshi);
    }
}
