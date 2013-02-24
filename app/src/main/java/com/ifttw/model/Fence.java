package com.ifttw.model;

import android.location.Location;

public class Fence {

    private String name = "";
    private Location location = null;
    private AbstractAction action = null;
    private double radius = 500.0; // Defined in meters.

    public Fence(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() { return name; }
    public Location getLocation() { return location; }
    public double getRadius() { return radius; }
    public AbstractAction getAction() { return action; }

    public void setAction(AbstractAction action) { this.action = action; }

}
