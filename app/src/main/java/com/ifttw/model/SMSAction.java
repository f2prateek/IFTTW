package com.ifttw.model;

import com.ifttw.model.AbstractAction;
import com.parse.ParseObject;

public class SMSAction extends AbstractAction {

    private String number = "";

    public SMSAction(String number) {
        name = "Send a text message.";
        id = "SMS";
        this.number = number;
    }

    public String getNumber() { return number; }

}