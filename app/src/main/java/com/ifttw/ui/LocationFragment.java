package com.ifttw.ui;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.ifttw.R;

import static com.ifttw.R.*;

public class LocationFragment extends RoboSherlockFragment {

    OnSubmitListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.location_fragment, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSubmitListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implemented OnSubmitListener.");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button submit = (Button) getActivity().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: Get the current location from our service.
                Location location = null;
                mListener.onSubmitted(location);
            }
        });
    }

    public interface OnSubmitListener {
        public void onSubmitted(Location location);
    }
}
