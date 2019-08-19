package com.orangeskill.elate.feature.playlist.ui.therapy.data;

import android.arch.lifecycle.MutableLiveData;

import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.PlayList;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.network.RestApi;
import com.orangeskill.elate.framework.network.RestApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListDataSource {
    private static final String TAG = PlayListDataSource.class.getSimpleName();
    public static PlayListDataSource instance;

    public static PlayListDataSource getInstance() {
        if (instance == null) {
            instance = new PlayListDataSource();
        }
        return instance;
    }

    public void loadPlayList(int id, MutableLiveData<PlayList> listMutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).getPlayList(id)
                .enqueue(new Callback<PlayList>() {
                    @Override
                    public void onResponse(Call<PlayList> call, Response<PlayList> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.d(TAG, ">> Play " + response.body().toString());
                            listMutableLiveData.postValue(response.body());
                        } else {
                            listMutableLiveData.postValue(null);
                            Logger.e(TAG, ">> false");
                        }
                    }

                    @Override
                    public void onFailure(Call<PlayList> call, Throwable t) {
                        Logger.e(TAG, " >> "  + t.getMessage());
                        listMutableLiveData.postValue(null);
                    }
                });
    }



}
