package com.ifttw.model;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAction {

    String name = "";
    String id = "";
    Map<String, String> pairs = new HashMap<String, String>();

    public abstract void addPair(String key, String value);
    public abstract void removePair(String key);
    public abstract String toJSON();

    @Override
    public String toString() {
        return name;
    }
}
