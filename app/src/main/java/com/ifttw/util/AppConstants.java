package com.ifttw.util;

import android.app.AlarmManager;

/**
 *
 */
public class AppConstants {

    /*
    Defaults
     */
    // The default search radius when searching for fences nearby.
    public static int DEFAULT_RADIUS = 150;
    // The maximum distance the user should travel between location updates.
    public static int MAX_DISTANCE = DEFAULT_RADIUS / 2;
    // The maximum time that should pass before the user gets a location update.
    public static long MAX_TIME = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    public static String ACTIVE_LOCATION_UPDATE_PROVIDER_DISABLED = "com.ifttw.active_location_update_provider_disabled";

}
