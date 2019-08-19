package com.orangeskill.elate.feature.playlist.ui.therapy;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.PlaylistItemBinding;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.Program;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListViewHolder> {

    private Context context;
    private List<Program> list;
    private LayoutInflater inflater;
    private OnItemClick clickListner;

    public PlayListAdapter(Context context, List<Program> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        PlaylistItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.playlist_item, viewGroup, false);
        return new PlayListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListViewHolder playListViewHolder, int i) {
        Program program = list.get(i);
        playListViewHolder.setBinding(program, context);
        playListViewHolder.binding.playImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onItemClick(program);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListner(OnItemClick onItemClick) {
        this.clickListner = onItemClick;
    }
}

class PlayListViewHolder extends RecyclerView.ViewHolder {
    PlaylistItemBinding binding;

    public PlayListViewHolder(@NonNull PlaylistItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setBinding(Program program, Context context) {
        binding.titleText.setText(program.getName());
        //Glide.with(context).load(program.getUrl()).into(binding.playImg);
    }
}

interface OnItemClick {
    void onItemClick(Program program);
}

