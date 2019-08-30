package com.orangeskill.elate.feature.feed;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.FeedListItemBinding;
import com.orangeskill.elate.feature.feed.data.model.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private Context context;
    private List<Feed> feeds;
    private LayoutInflater inflater;

    public FeedAdapter(Context feedActivity, List<Feed> feeds) {
        this.context = feedActivity;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        FeedListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.feed_list_item, viewGroup, false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {
        Feed feed = feeds.get(i);
        feedViewHolder.bind(feed, context);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }
}

class FeedViewHolder extends RecyclerView.ViewHolder {

    FeedListItemBinding binding;
    public FeedViewHolder(@NonNull FeedListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Feed feed, Context context) {
        binding.title.setText(feed.getAuthorName());
        binding.feedText.setText(feed.getText());
        Glide.with(context).load(feed.getLogoUrl()).into(binding.cornerImg);
        Glide.with(context).load(feed.getImageUrl()).into(binding.feedImage);
    }
}