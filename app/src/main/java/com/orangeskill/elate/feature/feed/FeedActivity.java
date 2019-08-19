package com.orangeskill.elate.feature.feed;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityFeedBinding;

public class FeedActivity extends AppCompatActivity {
    private static final String TAG = FeedActivity.class.getSimpleName();
    private ActivityFeedBinding binding;
    private FeedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        viewModel = ViewModelProviders.of(this).get(FeedViewModel.class);



    }
}
