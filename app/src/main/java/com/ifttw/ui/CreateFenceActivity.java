package com.ifttw.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.android.gms.maps.SupportMapFragment;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.parse.Parse;

public class CreateFenceActivity extends RoboSherlockFragmentActivity {

    public Fence fence = null;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, "D6ygFDR2M418xIgbT4fdWJKUpTubDKHG1ZxvaHzS", "8lj260b0W5DsCqrm0kWl4oCv4NLNHLPpT0kZKCWm");

        // Add the map fragment to select a location.
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = SupportMapFragment.newInstance();
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
                // TODO: Get location.
                double lat = 0.0;
                double lng = 0.0;
                fence = new Fence("NAME", null, lat, lng);
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
