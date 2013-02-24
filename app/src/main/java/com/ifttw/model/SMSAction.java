package com.ifttw.model;

import com.ifttw.model.AbstractAction;

public class SMSAction extends AbstractAction {

    public SMSAction() {
        name = "Send a text message.";
        id = "SMS";
    }

    @Override
    public void addPair(String key, String value) {
        addPair(key, value);
    }

    @Override
    public void removePair(String key) {
        removePair(key);
    }

    @Override
    public String toJSON() {
        return toJSON();
    }
}