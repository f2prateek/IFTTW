package com.ifttw.model;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAction {

    String name = "";
    String id = "";

    public String getName() { return name; }
    public String getID() { return id; }

    @Override
    public String toString() {
        return name;
    }
}