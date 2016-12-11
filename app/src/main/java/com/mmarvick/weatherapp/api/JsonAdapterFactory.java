package com.mmarvick.weatherapp.api;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by michael on 12/10/16.
 */

@MoshiAdapterFactory
public abstract class JsonAdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_JsonAdapterFactory();
    }
}
