

package com.ifttw;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.inject.Injector;
import com.google.inject.Stage;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import roboguice.RoboGuice;

import static com.ifttw.util.LogUtils.makeLogTag;

/**
 * If This Then What application
 */
public class IFTTWApplication extends Application {

    private static final String LOGTAG = makeLogTag(IFTTWApplication.class) ;

    /**
     * Create main application
     */
    public IFTTWApplication() {


    }

    /**
     * Create main application
     *
     * @param context
     */
    public IFTTWApplication(final Context context) {
        this();
        attachBaseContext(context);
    }

    /**
     * Create main application
     *
     * @param instrumentation
     */
    public IFTTWApplication(final Instrumentation instrumentation) {
        this();
        attachBaseContext(instrumentation.getTargetContext());
    }

    /**
     * Sets the application injector. Using the {@link RoboGuice#newDefaultRoboModule} as well as a
     * custom binding module {@link IFTTWModule} to set up your application module
     *
     * @param application
     * @return
     */
    public static Injector setApplicationInjector(Application application) {
        return RoboGuice.setBaseApplicationInjector(application, Stage.DEVELOPMENT, RoboGuice.newDefaultRoboModule
                (application), new IFTTWModule());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOGTAG, "starting application");

        Parse.initialize(this, "D6ygFDR2M418xIgbT4fdWJKUpTubDKHG1ZxvaHzS", "8lj260b0W5DsCqrm0kWl4oCv4NLNHLPpT0kZKCWm");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access while disabling public write access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        startService(new Intent(getBaseContext(), LocationService.class));
        setApplicationInjector(this);
    }

}
