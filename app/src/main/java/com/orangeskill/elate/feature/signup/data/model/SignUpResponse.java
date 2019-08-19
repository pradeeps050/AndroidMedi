package com.orangeskill.elate.feature.signup.data.model;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("Id")
    private Integer id;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("Password")
    private String password;

    @SerializedName("Salt")
    private String salt;

    @SerializedName("UserRoleId")
    private int userRoleId;

    @SerializedName("IsSubscribed")
    private Boolean isSubscribed;

    @SerializedName("SubscriptionPlanId")
    private Integer subscriptionPlanId;

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userRoleId=" + userRoleId +
                ", isSubscribed=" + isSubscribed +
                ", subscriptionPlanId=" + subscriptionPlanId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        isSubscribed = subscribed;
    }

    public Integer getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(Integer subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }
}
