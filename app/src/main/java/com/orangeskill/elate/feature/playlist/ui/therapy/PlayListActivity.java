package com.orangeskill.elate.feature.playlist.ui.therapy;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivityPlayListBinding;
import com.orangeskill.elate.feature.home.model.MainHeader;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.PlayList;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.Program;
import com.orangeskill.elate.feature.session.SessionActivity;
import com.orangeskill.elate.feature.video.VideoActivity;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity implements OnItemClick {

    private static final String TAG = PlayListActivity.class.getSimpleName();
    private ActivityPlayListBinding binding;
    private PlayListViewModel viewModel;
    private int id;
    private String description;
    private String subCategory;
    private Intent intent;
    private String imageUrl;
    private String curatedBy;
    private String name;
    private MainHeader mainHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        mainHeader = intent.getParcelableExtra("key");
        if (mainHeader != null) {
            id = mainHeader.getId();
        }

//        id = intent.getIntExtra(ConstantValues.PROGRAM_ID, 0);
//        description = intent.getStringExtra(ConstantValues.DESCRIPTION);
//        subCategory = intent.getStringExtra(ConstantValues.SUBCATEGORY);
//        imageUrl = intent.getStringExtra(ConstantValues.IMAGE_URL);
//        curatedBy = intent.getStringExtra(ConstantValues.CURATED_BY);
//        name = intent.getStringExtra(ConstantValues.NAME);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_list);
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(this).get(PlayListViewModel.class);
        initRecyclerView();
        binding.mainTitle.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onItemClick(Program program) {
        Logger.d(TAG, "On click >> " + program.toString());
        Intent intent = new Intent(PlayListActivity.this, VideoActivity.class);
        intent.putExtra(ConstantValues.VIDEO_LINK, program.getUrl());
        intent.putExtra(ConstantValues.VIDEO_NAME, program.getName());
        intent.putExtra(ConstantValues.SUBCATEGORY, mainHeader.getSubCategory());
        startActivity(intent);

    }

    private void initRecyclerView() {
        viewModel.loadPlayList(id);
        viewModel.getPlayList().observe(this, new Observer<PlayList>() {
            @Override
            public void onChanged(@Nullable PlayList playLists) {
                binding.progressBar.setVisibility(View.GONE);
                if (playLists != null) {
                    binding.mainTitle.setMainHeader(mainHeader);
                    /*Glide.with(PlayListActivity.this).load(imageUrl).into(binding.heading.mainImage);
                    binding.heading.nameText.setText(name);
                    binding.heading.noteText.setText(description);
                    binding.heading.curatedByTxt.setText(curatedBy);
                    binding.heading.subCatTxt.setText(subCategory);*/
                    //curatedplayList.getProgram();
                    PlayListAdapter adapter = new PlayListAdapter(PlayListActivity.this, playLists.getProgram());
                    adapter.setClickListner(PlayListActivity.this::onItemClick);
                    binding.videoRecyclerView.setLayoutManager(new LinearLayoutManager(PlayListActivity.this));
                    binding.videoRecyclerView.setAdapter(adapter);
                }
            }
        });
    }
}
