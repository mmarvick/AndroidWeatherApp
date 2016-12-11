package com.mmarvick.weatherapp.model;

import com.mmarvick.weatherapp.data.LocationMetadata;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by michael on 12/10/16.
 */

@Singleton
public class LocationModel {
    private LocationMetadata location;

    @Inject
    public LocationModel() {
        // Pass
    }

    public boolean hasLocation() {
        return location != null;
    }

    public LocationMetadata location() {
        return location;
    }

    public void setLocation(LocationMetadata location) {
        this.location = location;
    }
}
