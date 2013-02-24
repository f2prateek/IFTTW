package com.ifttw.model;

import com.parse.ParseObject;


public class Fence extends ParseObject {


    private static final String NAME = "Name";
    private static final String PARSE_NAME = "Fence";

    public Fence( String name ) {
        super( Fence.PARSE_NAME );

        this.add( Fence.NAME, name );

    }

    public String getFullName() {

        return (String) get( Fence.NAME );

    }

    public static String getObjectName() {

        return PARSE_NAME;

    }
}
