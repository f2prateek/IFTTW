package com.ifttw.model;

import com.ifttw.model.AbstractAction;
import com.parse.ParseObject;

public class SMSAction extends AbstractAction {

    private String message = "";
    private String number = "";

    public SMSAction(String message, String number) {
        name = "Send a text message.";
        id = "SMS";
        this.message = message;
        this.number = number;
    }

    public String getMessage() { return message; }
    public String getNumber() { return number; }

}