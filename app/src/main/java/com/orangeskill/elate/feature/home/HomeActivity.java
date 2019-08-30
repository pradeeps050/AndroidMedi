package com.orangeskill.elate.feature.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;


import com.agrawalsuneet.dotsloader.loaders.LinearDotsLoader;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityHomeBinding;
import com.orangeskill.elate.feature.feed.FeedActivity;
import com.orangeskill.elate.feature.home.model.TherapyCategory;
import com.orangeskill.elate.feature.session.SessionActivity;
import com.orangeskill.elate.feature.welcomeslide.WelcomeActivity;
import com.orangeskill.elate.framework.application.AppInstance;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.preference.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnItemClick {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private HomeAdapter adapter;
    private BottomNavigationView bottomMenu;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.heading.setVisibility(View.GONE);
        binding.bottomMenu.setVisibility(View.GONE);
        PreferenceHelper preferenceHelper = PreferenceHelper.getAppPrefs(AppInstance.getInstance().getContext());
        if (preferenceHelper.isFirstTimeLaunch()) {
            preferenceHelper.setFirstTimeLaunch(false);
            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
            finish();
        }
        binding.dotProgressBar.setVisibility(View.VISIBLE);
        loadRecyclerView();

        binding.bottomMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                loadRecyclerView();
                                break;
                            case R.id.nav_feed:
                                startActivity(new Intent(HomeActivity.this, FeedActivity.class));
                                break;
                            case R.id.profile_menu:
                                break;
                        }
                        return true;
                    }
                }
        );
    }

    private void loadRecyclerView() {
        viewModel.load();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.homeRecyclerView.setLayoutManager(gridLayoutManager);
        viewModel.getList().observe(this, new Observer<List<TherapyCategory>>() {
            @Override
            public void onChanged(@Nullable List<TherapyCategory> therapyCategories) {
                binding.dotProgressBar.setVisibility(View.GONE);
                binding.heading.setVisibility(View.VISIBLE);
                binding.bottomMenu.setVisibility(View.VISIBLE);
                if (therapyCategories != null && therapyCategories.size() != 0) {
                    adapter = new HomeAdapter(HomeActivity.this, therapyCategories);
                    binding.homeRecyclerView.setAdapter(adapter);
                    adapter.setOnClick(HomeActivity.this::onItemClick);
                }
            }
        });
    }

    @Override
    public void onItemClick(TherapyCategory category) {
        Logger.d(TAG, "Click deatils >> " + category.toString());
        Intent intent = new Intent(HomeActivity.this, SessionActivity.class);
        intent.putExtra(ConstantValues.THERAPY_CAT_ID, category.getId());
        startActivity(intent);
    }

}
