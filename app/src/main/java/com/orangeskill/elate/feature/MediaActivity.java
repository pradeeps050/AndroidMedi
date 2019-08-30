package com.orangeskill.elate.feature;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityMediaBinding;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;

public class MediaActivity extends AppCompatActivity {

    private static final String TAG = MediaActivity.class.getSimpleName();
    private ActivityMediaBinding binding;
    private Intent intent;
    private String link = "http://docs.google.com/gview?embedded=true&url=";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_media);
        intent = getIntent();
        url = intent.getStringExtra(ConstantValues.FILE_URL);
        String extension = intent.getStringExtra(ConstantValues.EXTENSION);
        binding.mediaWebView.getSettings().setJavaScriptEnabled(true);
        binding.mediaWebView.getSettings().setLoadWithOverviewMode(true);
        binding.mediaWebView.getSettings().setUseWideViewPort(true);
        binding.mediaWebView.getSettings().setBuiltInZoomControls(true);
        if (ConstantValues.PDF.equals(extension)) {
            url = link + url;
            binding.mediaWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return (false);
                }
            });
        }
        binding.mediaWebView.loadUrl(url);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
