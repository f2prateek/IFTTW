package com.ifttw.test;


import android.test.ActivityInstrumentationTestCase2;
import com.ifttw.ui.CreateFenceActivity;
import com.squareup.spoon.Spoon;

/**
 * Tests of displaying the authenticator activity
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<CreateFenceActivity> {

    /**
     * Create test for {@link com.ifttw.ui.CreateFenceActivity}
     */
    public MainActivityTest() {
        super(CreateFenceActivity.class);
    }

    /**
     * Verify activity exists
     */
    public void testActivityExists() {
        assertNotNull(getActivity());
        Spoon.screenshot(getActivity(), "initial_state");
    }
}

