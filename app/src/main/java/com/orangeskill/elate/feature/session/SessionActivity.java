package com.orangeskill.elate.feature.session;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivitySessionBinding;
import com.orangeskill.elate.feature.home.model.MainHeader;
import com.orangeskill.elate.feature.playlist.ui.therapy.PlayListActivity;
import com.orangeskill.elate.feature.session.model.Therapies;
import com.orangeskill.elate.feature.session.model.TherapySession;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends AppCompatActivity implements ItemClickListner {
    private static final String TAG = SessionActivity.class.getSimpleName();
    private ActivitySessionBinding binding;
    private SessionViewModel viewModel;
    private SessionAdapter adapter;
    private ArrayList<TherapySession> list;
    private int catId = 0;
    private String description;
    private String imageUrl;
    private String curatedBy;
    private String name;
    private MainHeader mainHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catId = getIntent().getIntExtra(ConstantValues.THERAPY_CAT_ID, 0);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_session);
        viewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        initRecyclerView();
        binding.pageHead.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initRecyclerView(){

        viewModel.getSession(catId);
        binding.progressbar.setVisibility(View.VISIBLE);
        binding.sessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getSessionLiveData().observe(this, new Observer<TherapySession>() {
            @Override
            public void onChanged(@Nullable TherapySession therapySessions) {
                binding.progressbar.setVisibility(View.GONE);
                if (therapySessions!= null) {
                    name = therapySessions.getName();
                    imageUrl = therapySessions.getThumbnailPath();
                    curatedBy = therapySessions.getCuratedBy();
                    String para = therapySessions.getDescription();
                    mainHeader = new MainHeader(imageUrl, name, "", para, curatedBy, 0);
//                    mainHeader.setImageUrl(therapySessions.getThumbnailPath());
//                    mainHeader.setName(therapySessions.getName());
//                    mainHeader.setNote(therapySessions.getDescription());
//                    mainHeader.setCuratedBy(therapySessions.getCuratedBy());
                    binding.pageHead.setMainHeader(mainHeader);

                    //Glide.with(SessionActivity.this).load(imageUrl).into(binding.heading.mainImage);
                    //binding.heading.nameText.setText(therapySessions.getName());
                    description = therapySessions.getDescription();
                    //binding.heading.noteText.setText(description);
                    //binding.heading.curatedByTxt.setText(curatedBy);
                    List<Therapies> therapies = therapySessions.getTherapies();
                    adapter = new SessionAdapter(SessionActivity.this, therapies);
                    binding.sessionRecyclerView.setAdapter(adapter);
                    adapter.setClickListner(SessionActivity.this);
                }
            }
        });
    }


    @Override
    public void onClickItem(Therapies therapies) {
        Logger.d(TAG, "Session >> " + therapies.toString());
        int id = therapies.getId();
        Intent intent = new Intent(SessionActivity.this, PlayListActivity.class);
        //subCat
        mainHeader.setSubCategory(therapies.getSubCatagoryName());
        mainHeader.setId(id);
        intent.putExtra("key", mainHeader);
//        intent.putExtra(ConstantValues.PROGRAM_ID, id);
//        intent.putExtra(ConstantValues.DESCRIPTION, description);
//        intent.putExtra(ConstantValues.SUBCATEGORY, therapies.getSubCatagoryName());
//        intent.putExtra(ConstantValues.CURATED_BY, curatedBy);
//        intent.putExtra(ConstantValues.IMAGE_URL, imageUrl);
//        intent.putExtra(ConstantValues.NAME, name);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
