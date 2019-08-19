package com.orangeskill.elate.framework.network;


import com.orangeskill.elate.BuildConfig;
import com.orangeskill.elate.framework.logger.Logger;


import java.io.File;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


public class RestApiBuilder {
    private static final String CACHE_CONTROL = "Cache-Control";

    private static final Retrofit mRetrofit = provideRetrofit(RestApi.API_BASE_URL);



    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addInterceptor(provideCacheInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(provideCache())
                .addInterceptor(provideHttpLoggingInterceptor())
                .build();
    }






    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    private static Interceptor provideOfflineCacheInterceptor() {
        return chain -> {
                Request request = chain.request();

                CacheControl cacheControl = new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build();
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .header("Content-Type", "application/json")
                        .header("User-Agent", "android")
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();

                return chain.proceed(request);
        };
    }



    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(com.orangeskill.elate.framework.application.ElateApplication.getInstance().getCacheDir(),
                    "http-cache-WindStream"), 10 * 1024 * 1024);
         } catch (Exception e) {
            Logger.d("",e.getMessage());
        }

        return cache;
    }

    private static Interceptor provideCacheInterceptor() {
        return chain ->  {
                okhttp3.Response response = chain.proceed(chain.request());
                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder().maxAge(200, TimeUnit.DAYS).build();

                return response.newBuilder().header(CACHE_CONTROL, cacheControl.toString()).build();
        };
    }




    private static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(getHttpClient(baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient getHttpClient(String baseUrl){

            return provideOkHttpClient();

    }


    private static X509TrustManager getTrustManger() {
        X509TrustManager x509TrustManager = null;
        try {
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init((KeyStore) null);
            for (TrustManager trustManager : tmf.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    Timber.d("Accepted issuers count : %s", x509TrustManager.getAcceptedIssuers().length);
                    break;
                }
            }
        } catch (Exception e) {
            Timber.d("Problem with X509 TrusManager : %s", e.getMessage());
        }
        return x509TrustManager;
    }

    private static OkHttpClient provideOkHttpClientForLogger() {
        return new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(provideLoggerAPIInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static Interceptor provideLoggerAPIInterceptor() {
        return chain -> {
            Request request = chain.request();
            request = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("x-account", "log")
                    .header("x-authkey", "log")
                    .build();
            return chain.proceed(request);
        };
    }

    private static <T> T providesNetworkService(boolean isLoginService, final Class<T> service) {

            return mRetrofit.create(service);
    }

    /*
    * @Deprecated use getNetworkService instead.
    * */

    public static RestApiServices providesService() {
        return  new RestApiServices(providesNetworkService(false, RestApi.class));
    }

    @Deprecated
    public static RestApiServices providesLoginService() {
        return new RestApiServices(providesNetworkService(true, RestApi.class));
    }

    public static <T> T getNetworkService(final Class<T> service) {
        return mRetrofit.create(service);
    }

    public static <T> T getNetworkService(final Class<T> service , int type) {
        switch (type){

            default:
                return mRetrofit.create(service);
        }
    }
}
