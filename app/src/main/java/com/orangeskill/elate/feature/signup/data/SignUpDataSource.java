package com.orangeskill.elate.feature.signup.data;

import android.arch.lifecycle.MutableLiveData;

import com.orangeskill.elate.feature.signup.SignUpActivity;
import com.orangeskill.elate.feature.signup.data.model.SignUpResponse;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.network.RestApi;
import com.orangeskill.elate.framework.network.RestApiBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpDataSource {
    private static final String TAG = SignUpActivity.class.getSimpleName();

    private static SignUpDataSource instance;

    public static SignUpDataSource getInstance() {
        if (instance == null) {
            instance = new SignUpDataSource();
        }
        return instance;
    }

    public void register(String mobile, String password, MutableLiveData<SignUpResponse> signUpResponseLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).register(mobile, password)
                .enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.i(TAG, " >> Signup response " + response.body().toString());
                            signUpResponseLiveData.postValue(response.body());
                        } else {
                            Logger.i(TAG, ">> Response is FALSE");
                            signUpResponseLiveData.postValue(null);
                        }

                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Logger.e(TAG, ">> onFail" + t.getMessage());
                        signUpResponseLiveData.postValue(null);

                    }
                });
    }
}
