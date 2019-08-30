package com.orangeskill.elate.feature.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.feature.feed.data.FeedDataSource;
import com.orangeskill.elate.feature.feed.data.model.Feed;

import java.util.List;

public class FeedViewModel extends ViewModel {

    private FeedDataSource dataSource;
    private MutableLiveData<List<Feed>> feedLiveData = new MutableLiveData<>();

    public FeedViewModel() {
        dataSource = FeedDataSource.getInstance();
    }

    public LiveData<List<Feed>> getFeedLiveData() {
        return feedLiveData;
    }

    public void getFeed() {
        dataSource.getFeed(feedLiveData);
    }
}
