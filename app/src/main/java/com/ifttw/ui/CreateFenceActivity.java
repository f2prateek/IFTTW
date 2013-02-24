package com.ifttw.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.ifttw.model.SMSAction;
import com.parse.ParseObject;
import roboguice.inject.InjectView;

import static com.ifttw.util.LogUtils.makeLogTag;

public class CreateFenceActivity extends RoboSherlockFragmentActivity implements View.OnClickListener {

    @InjectView(R.id.create_fence_button) Button submit_button;
    @InjectView(R.id.et_phone) EditText field_phone;
    @InjectView(R.id.et_fence) EditText fence_name;
    @InjectView(R.id.et_lng) EditText fence_lng;
    @InjectView(R.id.et_lat) EditText fence_lat;

    private static final String LOGTAG = makeLogTag(CreateFenceActivity.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fence);
        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = fence_name.getText().toString();
        double latitude = Double.parseDouble(fence_lng.getText().toString());
        double longitude = Double.parseDouble(fence_lat.getText().toString());

        //Fence
        Fence fence = new Fence(name, latitude, longitude);
        ParseObject fenceObj = fence.createParseObject();
        fenceObj.saveInBackground();

        //Action
        String number =  field_phone.getText().toString();
        SMSAction smsAction = new SMSAction(number);
        ParseObject action = new ParseObject("Action");
        action.put("fence", fenceObj);
        action.put("name", smsAction.getID());
        action.put("value", smsAction.getNumber());
        action.saveInBackground();

        finish();
    }
}