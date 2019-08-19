package com.orangeskill.elate.feature.home.data;

import android.arch.lifecycle.MutableLiveData;

import com.orangeskill.elate.feature.home.model.TherapyCategory;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.network.RestApi;
import com.orangeskill.elate.framework.network.RestApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDataSource {
    private static final String TAG = HomeDataSource.class.getSimpleName();
    private static HomeDataSource instance;

    public static HomeDataSource getInstance() {
        if (instance == null) {
            instance = new HomeDataSource();
        }
        return instance;
    }

    public void loadData(MutableLiveData<List<TherapyCategory>> listMutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).therapyCategory()
                .enqueue(new Callback<List<TherapyCategory>>() {
                    @Override
                    public void onResponse(Call<List<TherapyCategory>> call, Response<List<TherapyCategory>> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            listMutableLiveData.postValue(response.body());

                        } else {
                            Logger.e(TAG, "Response false Code >> " + response.code());
                            listMutableLiveData.postValue(null);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<TherapyCategory>> call, Throwable t) {
                        Logger.e(TAG, "Response onFail  >> " + call.toString());
                        listMutableLiveData.postValue(null);
                    }
                });
    }

}
