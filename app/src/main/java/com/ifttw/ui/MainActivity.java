package com.ifttw.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import roboguice.inject.InjectView;

public class MainActivity extends RoboSherlockFragmentActivity implements View.OnClickListener {

    @InjectView(R.id.button_test)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        LocationInfo latestInfo = new LocationInfo(this);
        Toast.makeText(this, latestInfo.toString(), Toast.LENGTH_LONG).show();
    }
}
