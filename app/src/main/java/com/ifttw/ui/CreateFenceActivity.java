package com.ifttw.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.parse.Parse;

import static com.ifttw.util.LogUtils.makeLogTag;

public class CreateFenceActivity extends RoboSherlockFragmentActivity {

    private static final String LOGTAG = makeLogTag(CreateFenceActivity.class);

    public Fence fence = null;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fence);

        Parse.initialize(this, "D6ygFDR2M418xIgbT4fdWJKUpTubDKHG1ZxvaHzS", "8lj260b0W5DsCqrm0kWl4oCv4NLNHLPpT0kZKCWm");

        // Add the map fragment to select a location.
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final Fragment fragment = SupportMapFragment.newInstance();
        ft.add(R.id.container, fragment);
        ft.commit();

        // Add the submit button.
        LinearLayout layout = (LinearLayout) findViewById(R.id.container);
        submit = new Button(this);
        submit.setText("Select Location");
        layout.addView(submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GoogleMap map = ((SupportMapFragment) fragment).getMap();
                LatLng location = map.getCameraPosition().target;
                double lat = location.latitude;
                double lng = location.longitude;
                Log.d(LOGTAG, "new fence at " + location.toString());
                fence = new Fence("HOME", lat, lng);
                // Replace the current fragment with the fragment to select an action.
                Fragment fragment = new SMSFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                submit.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        submit.setVisibility(View.VISIBLE);
    }
}