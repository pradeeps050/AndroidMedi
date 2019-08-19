package com.orangeskill.elate.feature.login.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Mobile")
    public String mobile;

    @SerializedName("Password")
    public String tokenType;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "mobile='" + mobile + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
