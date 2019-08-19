package com.orangeskill.elate.feature.session.data;

import android.arch.lifecycle.MutableLiveData;

import com.orangeskill.elate.feature.session.model.TherapySession;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.network.RestApi;
import com.orangeskill.elate.framework.network.RestApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionDataSource {
    private static final String TAG = SessionDataSource.class.getSimpleName();
    private static SessionDataSource instance;

    public static SessionDataSource getInstance() {
        if (instance == null) {
            instance = new SessionDataSource();
        }
        return instance;
    }

    public void loadSession(int id, MutableLiveData<TherapySession> sessionMutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).loadTherapySession(id)
                .enqueue(new Callback<TherapySession>() {
                    @Override
                    public void onResponse(Call<TherapySession> call, Response<TherapySession> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.d(TAG, " >>Data >> " + response.body());
                            sessionMutableLiveData.postValue(response.body());
                        } else {
                            Logger.d(TAG, ">> false");
                            sessionMutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<TherapySession> call, Throwable t) {
                        Logger.e(TAG, "Session load onFali >> " + t.getMessage());
                        sessionMutableLiveData.postValue(null);
                    }
                });

    }
}
