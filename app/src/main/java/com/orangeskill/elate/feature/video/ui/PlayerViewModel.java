package com.orangeskill.elate.feature.video.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.VideoView;

import com.orangeskill.elate.feature.video.data.DeviceConfig;

public class PlayerViewModel extends ViewModel {

    private MutableLiveData<DeviceConfig> mutableLiveData = new MutableLiveData<>();


    public PlayerViewModel() {
    }

    public LiveData<DeviceConfig> getMutableData() {
        return mutableLiveData;
    }
}
