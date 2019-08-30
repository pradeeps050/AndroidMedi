package com.orangeskill.elate.feature.feed.data;

import android.arch.lifecycle.MutableLiveData;

import com.orangeskill.elate.feature.feed.data.model.Feed;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.network.RestApi;
import com.orangeskill.elate.framework.network.RestApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDataSource {
    private static final String TAG = FeedDataSource.class.getSimpleName();
    private static FeedDataSource instance;

    public static FeedDataSource getInstance() {
        if (instance == null) {
            instance = new FeedDataSource();
        }
        return instance;
    }

    public void getFeed(MutableLiveData<List<Feed>> feedLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).getFeed().enqueue(new Callback<List<Feed>>() {
            @Override
            public void onResponse(Call<List<Feed>> call, Response<List<Feed>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Logger.d(TAG, "Feed " + response.body().size());
                    for (Feed feed : response.body()) {
                        Logger.d(TAG, "FEED >> " + feed.toString());
                    }
                    feedLiveData.postValue(response.body());
                } else {
                    Logger.d(TAG, " False >> " + response.code());
                    feedLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Feed>> call, Throwable t) {
                Logger.d(TAG, " False >> " + t.getMessage());
                feedLiveData.postValue(null);
            }
        });
    }

}
