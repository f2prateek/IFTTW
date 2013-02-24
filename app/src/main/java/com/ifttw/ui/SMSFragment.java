package com.ifttw.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.ifttw.model.SMSAction;
import com.parse.ParseObject;

import static com.ifttw.R.layout;

public class SMSFragment extends RoboSherlockFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(layout.sms_fragment, container, false);
        Button create = (Button) v.findViewById(R.id.create_fence_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number =  ((EditText) v.findViewById(R.id.et_phone)).getText().toString();
                Fence fence = ((CreateFenceActivity) getActivity()).fence;

                // Save in parse.
                ParseObject fenceObj = fence.createParseObject();
                fenceObj.saveInBackground();

                ParseObject po = new ParseObject("Action");
                SMSAction action = new SMSAction(number);
                po.put("fence", fenceObj);
                po.put("name", action.getID());
                po.put("value", action.getNumber());
                po.saveInBackground();
            }
        });
        return v;
    }
}