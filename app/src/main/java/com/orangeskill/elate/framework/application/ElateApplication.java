package com.orangeskill.elate.framework.application;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.orangeskill.elate.BuildConfig;
import com.orangeskill.elate.framework.Executors.AppExecutors;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class ElateApplication extends MultiDexApplication {

    private AppExecutors mAppExecutors;
    private boolean isAppInForeground;
    private WeakReference<Activity> mForegroundActivity;

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        initStrictMode();

        mAppExecutors = new AppExecutors();
        AppInstance appInstance = new AppInstance();
        appInstance.init(this);

        initTimberLogger();

    }

    public Context getContext() {
        return AppInstance.getInstance().getContext().getApplicationContext();
    }

    public static com.orangeskill.elate.framework.application.ElateApplication getInstance() {
        return AppInstance.getInstance().getContext();
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }

    public boolean isInternetConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null)
            activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
//                    .penaltyDeath()
                    .build());
        }
    }

    private void initTimberLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void appInForeground() {
        Logger.e(ConstantValues.LOG_TAG, "------------  ON START --------------");
        isAppInForeground = true;



    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void appInBackground() {
        Logger.e(ConstantValues.LOG_TAG, "------------  ON STOP --------------");
        isAppInForeground = false;

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void appNotVisible() {
        Logger.e(ConstantValues.LOG_TAG, "------------  ON PAUSE --------------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void appVisible() {
        Logger.e(ConstantValues.LOG_TAG, "------------  ON RESUME --------------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void appDestroyedVisible() {
        isAppInForeground = false;
    }

    public Activity getForegroundActivity() {
        if (mForegroundActivity == null)
            return null;
        return mForegroundActivity.get();
    }

    public void setForegroundActivity(Activity foregroundActivity) {
        this.mForegroundActivity = new WeakReference<>(foregroundActivity);
    }




    public AppExecutors getExecutors() {
        return mAppExecutors;
    }




    public boolean isAppInForeground() {
        return isAppInForeground;
    }







}

