package com.orangeskill.elate.framework.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.orangeskill.elate.framework.network.networkstate.OnAPIError;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;



public class NetworkError extends Throwable {
    public static final String DEFAULT_ERROR_MESSAGE = "Sorry, we are unable to load your information.";
    private static final String NETWORK_ERROR_MESSAGE = "No Internet Connection";
    public static final String USER_OFFLINE = "Device is offline";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    private static final String API_COMMUNICATION_ERROR = "Sorry, we are unable to load your information.";
    private static final String NO_CONTENT = "No content";
    private static final String NOT_MODIFIED = "Not modified";
    private static final String BAD_REQUEST = "Bad request";
    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String FORBIDDEN = "Forbidden";
    private static final String UNSUPPORTED_MEDIA_TYPE = "Unsupported media type";
    private final Throwable error;
    private int errorCode;
    private Context context;

    private OnAPIError onAPIError;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
        displayAppErrorMessage();
    }

    public NetworkError(Throwable e, OnAPIError listener) {
        super(e);
        this.error = e;
        onAPIError = listener;
        displayAppErrorMessage();
    }

    public String getMessage() {
        return error.getMessage();
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException && ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    @Nullable
    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;
        retrofit2.Response<?> response = ((HttpException) this.error).response();
        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) {
                return status;
            }

            Map<String, List<String>> headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER)) {
                return headers.get(ERROR_MESSAGE_HEADER).get(0);
            }
        }
        return DEFAULT_ERROR_MESSAGE;
    }

    //TODO check onApiError callback on paging scenario.
    @Nullable
    public NetworkState displayAppErrorMessage() {
        NetworkState mstate = null;
        String msg = DEFAULT_ERROR_MESSAGE;
        errorCode = 0;

        if(com.orangeskill.elate.framework.application.ElateApplication.getInstance() != null && com.orangeskill.elate.framework.application.ElateApplication.getInstance().isInternetConnected()) {
            if (error instanceof retrofit2.HttpException) {
                retrofit2.HttpException exception = (retrofit2.HttpException) error;
                errorCode = exception.code();
                switch (errorCode) {
                    case 204:
                        msg = NO_CONTENT;
                        break;
                    case 304:
                        msg = NOT_MODIFIED;
                        break;
                    case 400:
                        msg = BAD_REQUEST;
                        break;
                    case 401:
                        msg = UNAUTHORIZED;
                        break;
                    case 403:
                        msg = FORBIDDEN;
                        break;
                    case 404:
                        msg = API_COMMUNICATION_ERROR;
                        break;
                    case 415:
                        msg = UNSUPPORTED_MEDIA_TYPE;
                        break;
                    case 500:
                        msg = API_COMMUNICATION_ERROR;
                        break;
                    default:
                        msg = DEFAULT_ERROR_MESSAGE;
                        break;
                }

                mstate = new NetworkState(NetworkState.STATUS.ERROR, msg);
            }
            else if (this.error instanceof IOException)
            {
                msg = API_COMMUNICATION_ERROR;
                mstate = new NetworkState(NetworkState.STATUS.ERROR, msg);
            }
            //in case no internet connection no need for a callback to show snackbar.
            if(onAPIError != null) {
                onAPIError.onApiError(msg, errorCode);
            }
            if(mstate == null)
                mstate = new NetworkState(NetworkState.STATUS.ERROR);
        }
        else {
            mstate = new NetworkState(NetworkState.STATUS.INTERNET_ERROR, USER_OFFLINE);
        }
        return mstate;
    }

    @Nullable
    private String getJsonStringFromResponse(@NonNull final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            Response errorResponse = new Gson().fromJson(jsonString, Response.class);
            return errorResponse.status;
        } catch (Exception e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
