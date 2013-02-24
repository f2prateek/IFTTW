

package com.ifttw;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;
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

    @Override
    public void onCreate() {
        super.onCreate();

        LocationLibrary.showDebugOutput(true);

        //TODO: optimize
        LocationLibrary.initialiseLibrary(getBaseContext(), 60 * 1000, 2 * 60 * 1000, "com.ifttw");

        Log.d(LOGTAG, "library initialised");
        setApplicationInjector(this);
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
}
