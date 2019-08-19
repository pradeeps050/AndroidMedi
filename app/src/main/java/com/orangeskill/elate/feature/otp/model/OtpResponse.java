package com.orangeskill.elate.feature.otp.model;

import com.google.gson.annotations.SerializedName;

public class OtpResponse {

    @SerializedName("Id")
    private Integer id;

    @SerializedName("UserId")
    private Integer userId;

    @SerializedName("OTP")
    private String otp;

    @SerializedName("ValidTill")
    private String validTill;

    @SerializedName("Verified")
    private Boolean verified;

    @SerializedName("Mobile")
    private String mobile;
}
