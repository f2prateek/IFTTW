package com.ifttw;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import static com.ifttw.util.LogUtils.makeLogTag;

/**
 *
 */
public class LocationService extends Service implements LocationListener {

    private static final String LOGTAG = makeLogTag(LocationService.class);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(LOGTAG, "received "+ location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderEnabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderDisabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
