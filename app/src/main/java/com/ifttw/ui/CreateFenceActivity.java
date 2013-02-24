package com.ifttw.ui;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.ifttw.model.Fence;

public class CreateFenceActivity extends RoboSherlockFragmentActivity implements LocationFragment.OnSubmitListener {

    public Fence fence = null;

    @Override
    public void onSubmitted(Location location) {
        // Create the fence object.
        String name = "FENCENAMEGOESHERE";
        fence = new Fence(name, location);
        // Replace the current fragment with the fragment to select an action.
        Fragment fragment = new ActionFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add the fragment to select a location.
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = new LocationFragment();
        ft.add(R.id.container, fragment);
        ft.commit();
    }

}
