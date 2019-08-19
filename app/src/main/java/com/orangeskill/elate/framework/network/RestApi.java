package com.orangeskill.elate.framework.network;


import com.orangeskill.elate.feature.home.model.Therapy;
import com.orangeskill.elate.feature.home.model.TherapyCategory;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.PlayList;
import com.orangeskill.elate.feature.session.model.TherapySession;
import com.orangeskill.elate.feature.signup.data.model.SignUpResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RestApi {
    String API_BASE_URL = "http://3.220.157.39:90/api/"; //"http://3.220.157.39:91"

    @FormUrlEncoded
    @POST("Register")
    Call<SignUpResponse> register(@Field("Mobile") String mobile,
                                  @Field("Password") String password);

    @FormUrlEncoded
    @POST("Login")
    Call<ResponseBody> login(@Field("grant_type") String grant,
                             @Field("Mobile") String username,
                             @Field("Password") String password);


    @GET("TherapyCategory")
    Call<List<TherapyCategory>> therapyCategory();

    @GET("TherapyCategory/{Id}")
    Call<TherapySession> loadTherapySession(@Path("Id") int id);

    @GET("Therapy/{Id}")
    Call<PlayList> getPlayList(@Path("Id") int id);



}
