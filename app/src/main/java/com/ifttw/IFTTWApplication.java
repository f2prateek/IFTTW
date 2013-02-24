

package com.ifttw;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.FROYO;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.inject.Injector;
import com.google.inject.Stage;

import roboguice.RoboGuice;

/**
 * If This Then What application
 */
public class IFTTWApplication extends Application {

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
     * @param application
     * @return
     */
    public static Injector setApplicationInjector(Application application) {
        return RoboGuice.setBaseApplicationInjector(application, Stage.DEVELOPMENT, RoboGuice.newDefaultRoboModule
                (application), new IFTTWModule());
    }
}
