package com.ifttw.model;

import android.location.Location;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

public class Fence {

    private String name = "";
    private double latitude = 0.0;
    private double longitude = 0.0;
    private AbstractAction action = null;
    private double radius = 500.0; // Defined in meters.

    public Fence(String name, AbstractAction action, double latitude, double longitude) {
        this.name = name;
        this.action = action;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ParseObject createParseObject() {
        ParseObject po = new ParseObject("Fence");
        po.put("name", name);
        po.put("geopoint", new ParseGeoPoint(latitude, longitude));
        return po;
    }

    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getRadius() { return radius; }
    public AbstractAction getAction() { return action; }

    public void setAction(AbstractAction action) { this.action = action; }

}
