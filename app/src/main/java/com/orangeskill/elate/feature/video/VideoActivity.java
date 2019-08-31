package com.orangeskill.elate.feature.video;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.TimeUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityVideoBinding;
import com.orangeskill.elate.feature.feed.FeedActivity;
import com.orangeskill.elate.feature.session.SessionActivity;
import com.orangeskill.elate.feature.video.ui.CustomMediaController;
import com.orangeskill.elate.feature.video.ui.CustomVideoView;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class VideoActivity extends Activity implements CustomMediaController.FullScreen {

    private static final String TAG = VideoActivity.class.getSimpleName();
    private ActivityVideoBinding binding;
    private CustomVideoView videoView;
    private MediaController mediaController;
    final int REQUEST_CODE = 5000;
    private BottomNavigationView bottomNavigationView;  //"https://www.radiantmediaplayer.com/media/bbb-360p.mp4";
    private String videoUrl;
    private String videoName;
    private String subCat;
    private  Intent intent;
    private boolean isComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video);
        videoView = findViewById(R.id.video);
        bottomNavigationView = binding.bottomMenu;
        binding.videoName.setText(intent.getStringExtra(ConstantValues.VIDEO_NAME));
        binding.subCatText.setText(intent.getStringExtra(ConstantValues.SUBCATEGORY));
        mediaController = new MediaController(this);
        //mediaController.setToggle(this::clicked);
        loadPlayer();
        videoView.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {
            @Override
            public void onPlay() {
                Logger.d(TAG, " >> play");
                if (isComplete) {
                    videoView.start();
                }

            }

            @Override
            public void onPause() {
                Logger.d(TAG, " >> onpause");
                //loadPlayer();

            }

        });

        //videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Logger.d(TAG, " >> onCompletion");
                isComplete = true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, ">> onSaveInstanceState");
        if (videoView.isPlaying()) {
            Log.d(TAG, ">> playing true");
            outState.putInt("key", videoView.getCurrentPosition());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.d(TAG, " >> onRestoreInstanceState");
        if (savedInstanceState != null) {
            int i = savedInstanceState.getInt("key");
            videoView.seekTo(i);
            videoView.start();
        }
    }

    private void loadPlayer() {
        videoUrl = intent.getStringExtra(ConstantValues.VIDEO_LINK);
        //String fullScreen = intent.getStringExtra("fullScreenInd");
        //download(videoUrl);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                Logger.d(TAG, " >> onPrepared");
                boolean running = true;
                final int duration = videoView.getDuration();
                new Thread(new Runnable() {
                    public void run() {
                        do{
                            binding.time.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    long minutes = time/60;
                                    long seconds = time%60;
                                    binding.time.setText("Duration : " + minutes + ":" + seconds);
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();
            }
        });


        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_feed:
                        startActivity(new Intent(VideoActivity.this, FeedActivity.class));
                        break;
                    case R.id.profile_menu:
                        break;
                }

                return false;
            }
        });

    }



    public void download(String url) {
        DownloadManager manager = (DownloadManager) this.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        String fileName = "android.resource://"+  getPackageName() + "/raw/video";
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Downloading...");
        request.setDescription(uri.getLastPathSegment());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(fileName,"SampleVideo.mp4");
        manager.enqueue(request);
    }

        /*if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getSupportActionBar().hide();
            int currentTime = intent.getIntExtra("time", 0);
            *//*if (currentTime > 0) {
                if (videoView != null) {
                    Logger.i(TAG, ">> video is null");
                    videoView.start();
                    videoView.seekTo(currentTime);
                }
            }*//*
            mediaController = new CustomMediaController(this, videoView);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(videoUrl));
            videoView.requestFocus();
            videoView.start();
            }*/


    @Override
    public void clicked() {
        Logger.d(TAG, "landscape >> " + isLandScape());
        if (isLandScape()) {
            binding.bottomMenu.setVisibility(View.GONE);
            int i = videoView.getCurrentPosition();
            videoView.seekTo(i);

            videoView.start();
        } else {
            binding.bottomMenu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.d(TAG, " >> onConfigurationChanged");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(VideoActivity.this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(VideoActivity.this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLandScape(){
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            return true;
        }
        return false;
    }


}
