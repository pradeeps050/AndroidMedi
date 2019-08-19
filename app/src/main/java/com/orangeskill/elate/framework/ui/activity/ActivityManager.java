package com.orangeskill.elate.framework.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;


import com.orangeskill.elate.framework.constantsValues.ConstantValues;

import java.util.Objects;



public class ActivityManager {

    private static ActivityManager mActivityManager;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (mActivityManager == null) {
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    public void startActivity(AppScreens screen) {
        startActivity(screen, null, false, null);
    }

    public void startActivity(AppScreens screen , boolean isFinish) {
        startActivity(screen, null, isFinish, null);
    }

    public void startActivity(AppScreens screen, Bundle bundle) {
        startActivity(screen, bundle, false, null);
    }

    public void startActivity(AppScreens screen, Bundle bundle, boolean isFinish) {
        startActivity(screen, bundle, isFinish, null);
    }

    private void startActivity(@NonNull AppScreens screen, Bundle bundle, boolean finish,
                               ActivityOptionsCompat options) {
        startActivityForResult(screen, bundle, finish, options);
    }

    private void startActivityForResult(AppScreens screen, Bundle bundle, boolean finish, ActivityOptionsCompat options) {
        Intent intent = new Intent();
        switch (screen) {

        }

        if (bundle != null) {
            intent.putExtras(bundle);
            intent.putExtra(ConstantValues.BUNDLE_DATA, bundle);
        }


//
//        if (null == WindstreamApplication.getInstance().getForegroundActivity())
//            return;
//
//        WindstreamApplication.getInstance().getForegroundActivity().startActivity(intent);
//        if(finish){
//            WindstreamApplication.getInstance().getForegroundActivity().finish();
//        }
    }
}
