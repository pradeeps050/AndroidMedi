package com.orangeskill.elate.feature.session;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivitySessionBinding;
import com.orangeskill.elate.feature.MediaActivity;
import com.orangeskill.elate.feature.home.model.MainHeader;
import com.orangeskill.elate.feature.playlist.ui.therapy.PlayListActivity;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.Program;
import com.orangeskill.elate.feature.session.data.ExpdListDataSource;
import com.orangeskill.elate.feature.session.model.Therapies;
import com.orangeskill.elate.feature.session.model.TherapySession;
import com.orangeskill.elate.feature.video.VideoActivity;
import com.orangeskill.elate.framework.constantsValues.ConstantValues;
import com.orangeskill.elate.framework.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SessionActivity extends AppCompatActivity implements ItemClickListner {
    private static final String TAG = SessionActivity.class.getSimpleName();
    private ActivitySessionBinding binding;
    private SessionViewModel viewModel;
    //private SessionAdapter adapter;
    private ArrayList<TherapySession> list;
    private int catId = 0;
    private String description;
    private String imageUrl;
    private String curatedBy;
    private String name;
    private MainHeader mainHeader;
    private TextView overviewText;
    private RecyclerView listOfTherapies;
    private ExpdListAdapter adapter;
    private boolean flag = true;
    private String overView;
    private boolean overviewFlag = true;
    private HashMap<ExpdListDataSource.Group, List<Program>> map;
    private ArrayList<ExpdListDataSource.Group> groupArrayList;
    private TherapySession therapySessions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catId = getIntent().getIntExtra(ConstantValues.THERAPY_CAT_ID, 0);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_session);
        viewModel = ViewModelProviders.of(this).get(SessionViewModel.class);
        overviewText = (TextView) findViewById(R.id.tv_overview2);
        //listOfTherapies= findViewById(R.id.session_recycler_view);
        initRecyclerView();

        binding.pageHead.overviewContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                binding.pageHead.tvOverviewDesc.setText(overView);
                if (overviewFlag) {
                    binding.pageHead.tvOverviewDesc.setVisibility(View.VISIBLE);
                    binding.pageHead.imageOverview.setImageResource(R.drawable.ic_down_side_arrow_button);
                    overviewFlag = false;
                } else {
                    binding.pageHead.tvOverviewDesc.setVisibility(View.GONE);
                    binding.pageHead.imageOverview.setImageResource(R.drawable.ic_right_side_arrow_button);
                    overviewFlag = true;
                }

            }
        });
        binding.pageHead.scheduleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    binding.expandableListView.setVisibility(View.VISIBLE);
                    binding.pageHead.imageSchedule.setImageResource(R.drawable.ic_down_side_arrow_button);
                    flag = false;
                } else {
                    binding.expandableListView.setVisibility(View.GONE);
                    binding.pageHead.imageSchedule.setImageResource(R.drawable.ic_right_side_arrow_button);
                    flag = true;
                }
                //binding.pageHead.imageSchedule.setImageResource(R.drawable.ic_down_side_arrow_button);
                //initRecyclerView();
                //binding.expandableListView.setVisibility(View.VISIBLE);
                //listOfTherapies.setVisibility((listOfTherapies.getVisibility()==View.VISIBLE? View.GONE:View.VISIBLE));
            }
        });

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

        viewModel.getSessionLiveData().observe(this, new Observer<TherapySession>() {
            @Override
            public void onChanged(@Nullable TherapySession therapySessions) {
                binding.progressbar.setVisibility(View.GONE);
                if (therapySessions!= null) {
                    SessionActivity.this.therapySessions = therapySessions;
                    name = therapySessions.getName();
                    imageUrl = therapySessions.getThumbnailPath();
                    curatedBy = therapySessions.getCuratedBy();
                    String para = therapySessions.getDescription();
                    mainHeader = new MainHeader(imageUrl, name, "", para, curatedBy, 0);
//                    mainHeader.setImageUrl(therapySessions.getThumbnailPath());
//                    mainHeader.setName(therapySessions.getName());
//                    mainHeader.setNote(therapySessions.getDescription());
//                    mainHeader.setCuratedBy(therapySessions.getCuratedBy());
                    overView = therapySessions.getOverview();
                    //binding.pageHead.tvOverview.setText(therapySessions.getOverview());
                    binding.pageHead.setMainHeader(mainHeader);

                    Glide.with(SessionActivity.this).load(therapySessions.getCuratedByImage()).into(binding.pageHead.profileImage);
                    //binding.heading.nameText.setText(therapySessions.getName());
                    description = therapySessions.getDescription();
                    //binding.heading.noteText.setText(description);
                    //binding.heading.curatedByTxt.setText(curatedBy);
                    List<Therapies> therapies = therapySessions.getTherapies();
                    ExpdListDataSource expdListDataSource = new ExpdListDataSource();
                    map = expdListDataSource.setListData(therapies);
                    //ArrayList<Group> titleList = new ArrayList<Group>(map.keySet());
                    Set<ExpdListDataSource.Group> titleSet =  map.keySet();

                    //ExpdListDataSource.Group[] groups = (ExpdListDataSource.Group[]) titleSet.toArray();
                    Iterator<ExpdListDataSource.Group> iterator = titleSet.iterator();
                    groupArrayList = new ArrayList<ExpdListDataSource.Group>();
                    while (iterator.hasNext()) {
                        groupArrayList.add(iterator.next());
                    }

                    ExpdListAdapter adapter = new ExpdListAdapter(SessionActivity.this, groupArrayList, map);
                    binding.expandableListView.setAdapter(adapter);




                    //adapter = new SessionAdapter(SessionActivity.this, therapies);
                    //binding.sessionRecyclerView.setAdapter(adapter);
                    //adapter.setClickListner(SessionActivity.this);
                }
            }
        });

        binding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Program program = map.get(groupArrayList.get(groupPosition)).get(childPosition);
                Logger.d(TAG, "Program >> " + program.toString());
                String uri = program.getUrl();
                String extension = uri.substring(uri.lastIndexOf("."));
                Logger.d(TAG, " EXt >> " + extension);
                if (ConstantValues.GIF.equals(extension) || ConstantValues.PDF.equals(extension)) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(program.getUrl()));
                    browserIntent.setClass(SessionActivity.this, MediaActivity.class);
                    browserIntent.putExtra(ConstantValues.FILE_URL, program.getUrl());
                    browserIntent.putExtra(ConstantValues.EXTENSION, extension);
                    startActivity(browserIntent);
                } else if (".mp4".equals(extension)) {
                    Intent intent = new Intent(SessionActivity.this, VideoActivity.class);
                    intent.putExtra(ConstantValues.VIDEO_LINK, program.getUrl());
                    intent.putExtra(ConstantValues.VIDEO_NAME, program.getName());
                    intent.putExtra(ConstantValues.SUBCATEGORY, therapySessions.getTherapies().get(groupPosition).getSubCatagoryName());
                    startActivity(intent);
                }
                return false;
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
