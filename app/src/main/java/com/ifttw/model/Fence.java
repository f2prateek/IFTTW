package com.ifttw.model;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;


public class Fence {

    private String name = "";
    private double latitude = 0.0;
    private double longitude = 0.0;


    public Fence(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Fence(ParseObject object) {
        this.name = object.getString("name");
        ParseGeoPoint point = object.getParseGeoPoint("geopoint");
        this.latitude = point.getLatitude();
        this.longitude = point.getLongitude();
    }

    public ParseObject createParseObject() {
        ParseObject po = new ParseObject("Fence");
        po.put("name", name);
        po.put("geopoint", new ParseGeoPoint(latitude, longitude));
        return po;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
