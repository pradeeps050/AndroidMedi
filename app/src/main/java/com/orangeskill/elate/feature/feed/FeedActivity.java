package com.orangeskill.elate.feature.feed;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityFeedBinding;
import com.orangeskill.elate.feature.feed.data.model.Feed;
import com.orangeskill.elate.feature.home.HomeActivity;

import java.util.List;

import retrofit2.http.GET;

public class FeedActivity extends AppCompatActivity {
    private static final String TAG = FeedActivity.class.getSimpleName();
    private ActivityFeedBinding binding;
    private FeedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        viewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        binding.feedProgressbar.setVisibility(View.VISIBLE);
        loadFeed();
        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        onBackPressed();
                        break;
                    case R.id.nav_feed:
                        break;
                    case R.id.profile_menu:
                        break;
                }
                return true;
            }
        });
    }

    private void loadFeed() {
        viewModel.getFeed();
        viewModel.getFeedLiveData().observe(this, new Observer<List<Feed>>() {
            @Override
            public void onChanged(@Nullable List<Feed> feeds) {
                binding.feedProgressbar.setVisibility(View.GONE);
                if (feeds != null && feeds.size() != 0) {
                    FeedAdapter adapter = new FeedAdapter(FeedActivity.this, feeds);
                    binding.feedList.setLayoutManager(new LinearLayoutManager(FeedActivity.this));
                    binding.feedList.setAdapter(adapter);
                }
            }
        });
    }
}
