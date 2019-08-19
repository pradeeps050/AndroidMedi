package com.orangeskill.elate.feature.feed.data;

public class FeedDataSource {
    private static FeedDataSource instance;

    public FeedDataSource getInstance() {
        if (instance == null) {
            instance = new FeedDataSource();
        }
        return instance;
    }


}
