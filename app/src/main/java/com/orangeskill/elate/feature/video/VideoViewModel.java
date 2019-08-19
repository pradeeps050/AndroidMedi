package com.orangeskill.elate.feature.video;

import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.feature.video.data.VideoDataSource;

public class VideoViewModel extends ViewModel {
    private VideoDataSource dataSource;

    public VideoViewModel() {
        dataSource = VideoDataSource.getInstance();
    }


}
