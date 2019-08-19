package com.orangeskill.elate.feature.video.data;

import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.orangeskill.elate.framework.application.AppInstance;

import static android.content.Context.WINDOW_SERVICE;

public class DeviceConfig {

    boolean change;

    public boolean getOrientation() {
        return change = isLandScape();
    }

    private boolean isLandScape(){
        Display display = ((WindowManager)AppInstance.getInstance().getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            return true;
        }
        return false;
    }
}
