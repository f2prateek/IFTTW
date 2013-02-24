package com.ifttw.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.ifttw.model.SMSAction;
import com.parse.ParseObject;

import static com.ifttw.R.layout;

public class SMSFragment extends RoboSherlockFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button create = (Button) getActivity().findViewById(R.id.create_fence_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "DEFAULT MESSAGE";
                String number = "7801111111";
                Fence fence = ((CreateFenceActivity) getActivity()).fence;
                fence.setAction(new SMSAction(message, number));

                // Save in parse.
                ParseObject fenceObj = fence.createParseObject();
                fenceObj.saveInBackground();

                ParseObject po = new ParseObject("Action");
                SMSAction action = (SMSAction) fence.getAction();
                po.put("fence", fenceObj);
                po.put("name", action.getName());
                po.put("value", action.getNumber());
                po.saveInBackground();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.sms_fragment, container, false);
    }
}
