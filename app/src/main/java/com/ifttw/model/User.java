package com.ifttw.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class User extends ParseUser {

    //Possibly need for Facebook
    //private int token;

    private static final String FULL_NAME = "full_name";
    private static final String PARSE_NAME = "User";

    public User( String name, String username, String password ) {

        setUsername(username);
        setPassword(password);
        this.put( User.FULL_NAME, name );

    }

    public User( String username, String password) {

        try {
            logIn(username, password);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getFullName() {

        return (String) get( FULL_NAME );

    }

    public String getObjectName() {

        return PARSE_NAME;

    }

}
