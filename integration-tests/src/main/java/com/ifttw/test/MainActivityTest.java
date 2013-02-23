package com.ifttw.test;


import android.test.ActivityInstrumentationTestCase2;
import com.ifttw.ui.MainActivity;
import com.squareup.spoon.Spoon;

/**
 * Tests of displaying the authenticator activity
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Create test for {@link MainActivity}
     */
    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * Verify activity exists
     */
    public void testActivityExists() {
        assertNotNull(getActivity());
        Spoon.screenshot(getActivity(), "initial_state");
    }
}

