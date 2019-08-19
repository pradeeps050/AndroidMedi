package com.orangeskill.elate.feature.feed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class FeedViewHolder extends RecyclerView.ViewHolder {

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}