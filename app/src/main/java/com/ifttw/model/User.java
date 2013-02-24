package com.ifttw.model;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class User extends ParseUser {

    //Possibly need for Facebook
    //private int token;

    private static final String FULL_NAME = "Full Name";
    private static final String PARSE_NAME = "User";

    public User( String name, String username, String password ) {

        setUsername( username );
        setPassword( password );
        this.add( User.FULL_NAME, name );

    }

    public String getFullName() {

        return (String) get( FULL_NAME );

    }

    public String getObjectName() {

        return PARSE_NAME;

    }

}
