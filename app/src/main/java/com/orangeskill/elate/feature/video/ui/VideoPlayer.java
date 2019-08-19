package com.orangeskill.elate.feature.video.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class VideoPlayer extends VideoView {

    private int measuredWidth = 0;
    private int measuredHeight = 0;

    public VideoPlayer(Context context, int measuredWidth, int measuredHeight) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int measuredWidth, int measuredHeight) {
        super(context, attrs);

    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr, int measuredWidth, int measuredHeight) {
        super(context, attrs, defStyleAttr);

    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int measuredWidth, int measuredHeight) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public void setNewDimension(int width, int height) {
        this.measuredHeight = height;
        this.measuredWidth = width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
