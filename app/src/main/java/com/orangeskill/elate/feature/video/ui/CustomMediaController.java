package com.orangeskill.elate.feature.video.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.orangeskill.elate.R;
import com.orangeskill.elate.feature.video.VideoActivity;
import com.orangeskill.elate.framework.logger.Logger;

import static android.view.Gravity.TOP;

public class CustomMediaController extends MediaController {
    private static final String TAG = CustomMediaController.class.getSimpleName();
    private ImageButton fullScreen;
    private String isFullScreen;
    private Context context;
    private VideoView videoView;
    final int REQUEST_CODE = 5000;
    private FullScreen toggle;

    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public CustomMediaController(Context context, VideoView videoView) {
        super(context);
        this.context = context;
        this.videoView = videoView;
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);
        fullScreen = new ImageButton(super.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.RIGHT|TOP;
        params.rightMargin = 80;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen = ((Activity) getContext()).getIntent().getStringExtra("fullScreenInd");
        Logger.d(TAG, ">> ?? " + isFullScreen);
        fullScreen.setImageResource(R.drawable.ic_menu_camera);
       /* if ("y".equals(isFullScreen)) {
            fullScreen.setImageResource(R.drawable.ic_menu_camera);
        } else {
            fullScreen.setImageResource(R.drawable.ic_menu_send);
        }*/
        fullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isFullScreen = ((Activity) getContext()).getIntent().getStringExtra("fullScreenInd");
                //Logger.d(TAG, ">> " + isFullScreen);
                toggle.clicked();

                /*Intent intent = new Intent(context, VideoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if("y".equals(isFullScreen)){
                    intent.putExtra("fullScreenInd", "");
                }else{
                    intent.putExtra("fullScreenInd", "y");
                }
                int videoPosition = videoView.getCurrentPosition();
                intent.putExtra("time", videoPosition);
                ((Activity) getContext()).setResult(REQUEST_CODE);
                getContext().startActivity(intent);
                ((Activity) getContext()).finish();*/
                //context.startActivity(intent);
            }
        });
    }

    public void setToggle(FullScreen toggle) {
        this.toggle = toggle;
    }

    public interface FullScreen {
        void clicked();
    }
}

