package com.orangeskill.elate.feature.video.data;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

public class VideoDataSource {
    private static VideoDataSource dataSource;

    public static VideoDataSource getInstance() {
        if (dataSource == null) {
            dataSource = new VideoDataSource();
        }
        return dataSource;
    }

    public void downloadVideo() {
       /* DownloadManager manager = (DownloadManager) this.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        String fileName = "android.resource://"+  getPackageName() + "/raw/video";
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Downloading...");
        request.setDescription(uri.getLastPathSegment());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(fileName,"SampleVideo.mp4");
        manager.enqueue(request);*/
    }
}
