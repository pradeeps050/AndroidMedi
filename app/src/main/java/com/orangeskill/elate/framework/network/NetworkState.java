package com.orangeskill.elate.framework.network;

import android.content.Context;
import android.text.TextUtils;

import com.orangeskill.elate.R;
import com.orangeskill.elate.framework.logger.Logger;

public class NetworkState {
    private String message;
    private String descText;
    private String headerTxt;
    private String btnText;
    public STATUS status;
    private int imgResId;

    public NetworkState(STATUS status, String message) {
        this.status = status;
        this.message = message;

        setFieldsForState(status);

        if (status == STATUS.START_ERROR)
            Logger.d("NetworkState", "OnError");
    }

    public NetworkState(STATUS status) {
        this.status = status;
        setFieldsForState(status);
    }

    public NetworkState(STATUS status, String descText, String headerText, String btnTxt, int imgResId) {
        this.status = status;
        setFields(descText, headerText, btnTxt, imgResId);
    }

    private void setFieldsForState(STATUS status) {
        Context context = com.orangeskill.elate.framework.application.ElateApplication.getInstance().getContext();
        switch (status) {
            case INTERNET_ERROR:
                setFields(context.getString(R.string.no_internet_connection_msg),
                        context.getString(R.string.no_internet_connection_msg_header),
                        context.getString(R.string.retry), R.drawable.ic_menu_share);
                break;
            case START_ERROR:
            case ERROR:
                setFields(context.getString(R.string.api_error_msg),
                        context.getString(R.string.api_error_header_msg),
                        context.getString(R.string.retry), R.drawable.ic_menu_share);
                break;
            case NO_DATA:
                String headerText = message;
                if(TextUtils.isEmpty(headerText)){
                    headerText = context.getString(R.string.api_error_msg);
                }
                setFields("",
                        headerText, null, R.drawable.ic_menu_share);
                break;

            case NO_SEARCH_RESULT:
                setFields("",
                        context.getString(R.string.api_error_msg), null, R.drawable.ic_menu_share);
                break;
        }
    }

    private void setFields(String descText, String headerText, String btnText, int imgResId) {
        setDescText(descText);
        setHeaderTxt(headerText);
        setBtnText(btnText);
        setImageRosourceId(imgResId);
    }

    public enum STATUS {START, LOADING, LOADED, ERROR, START_ERROR, NO_DATA, INTERNET_ERROR, AUTH_ERROR, NO_FILTERED_DATA, NO_SEARCH_RESULT}

    //     TODO: Change this to getMessage, when RestAPIBuilder starts giving appropriate error message.
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setImageRosourceId(int imageRosource) {
        imgResId = imageRosource;
    }

    public int getImageResourceId() {
        return imgResId;
    }

    public void setHeaderTxt(String text) {
        headerTxt = text;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getHeaderTxt() {
        return headerTxt;
    }

    public void setBtnText(String text) {
        btnText = text;
    }

    public String getBtnText() {
        return btnText;
    }
}
