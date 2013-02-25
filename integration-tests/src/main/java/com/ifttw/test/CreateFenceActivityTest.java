package com.ifttw.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import com.ifttw.R;
import com.ifttw.ui.CreateFenceActivity;
import com.squareup.spoon.Spoon;

/**
 *
 */
public class CreateFenceActivityTest extends ActivityInstrumentationTestCase2<CreateFenceActivity> {

    private CreateFenceActivity activity;
    private Instrumentation instrumentation;

    /**
     * Create test for {@link com.ifttw.ui.CreateFenceActivity}
     */
    public CreateFenceActivityTest() {
        super(CreateFenceActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        instrumentation = getInstrumentation();
    }

    /**
     * Verify activity exists
     */
    public void testActivityExists() {
        assertNotNull(getActivity());
        Spoon.screenshot(getActivity(), "initial_state");
    }

    /**
     * Verify activity exists
     */
    public void testFenceCreated() {
        assertNotNull(getActivity());
        Spoon.screenshot(getActivity(), "initial_state");

        final EditText name = (EditText) activity.findViewById(R.id.et_fence);
        final EditText longitude = (EditText) activity.findViewById(R.id.et_lng);
        final EditText latitude = (EditText) activity.findViewById(R.id.et_lat);
        final EditText number = (EditText) activity.findViewById(R.id.et_phone);

        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                name.setText("Test1");
                longitude.setText("35");
                latitude.setText("35");
                number.setText("7803181058");
            }
        });
        instrumentation.waitForIdleSync();

        Spoon.screenshot(getActivity(), "filled");

        final Button submit = (Button) activity.findViewById(R.id.create_fence_button);
        // Click the "submit" button.
        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                submit.performClick();
            }
        });
        instrumentation.waitForIdleSync();

    }

}
