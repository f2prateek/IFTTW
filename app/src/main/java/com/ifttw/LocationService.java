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
import com.parse.*;

import java.util.List;

import static com.ifttw.util.LogUtils.makeLogTag;

/**
 *
 */
public class LocationService extends Service implements LocationListener {

    private static final String LOGTAG = makeLogTag(LocationService.class);

    private static ParseGeoPoint locationToGeoPoint(Location l) {
        return new ParseGeoPoint(l.getLatitude(), l.getLongitude());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOGTAG, "starting service");
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(LOGTAG, "got location " + location.toString());

        ParseQuery query = new ParseQuery("Fence");
        query.whereNear("geopoint", locationToGeoPoint(location));
        query.setLimit(1);
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Log.d(LOGTAG, parseObjects.size()+"");
                if(parseObjects.size()==1) {
                    ParseObject checkin = new ParseObject("Checkin");
                    checkin.put("fence",parseObjects.get(0));
                    checkin.saveInBackground();
                }
            }
        });


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
