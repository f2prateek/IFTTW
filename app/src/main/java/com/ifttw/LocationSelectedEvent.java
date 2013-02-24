package com.ifttw;

/**
 *        Trigger when user selects a location
 */
public class LocationSelectedEvent {

    private double latitude;
    private double longitude;

    public LocationSelectedEvent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
