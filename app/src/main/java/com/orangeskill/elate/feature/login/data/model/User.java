package com.orangeskill.elate.feature.login.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class User {

    @SerializedName("UserId")
    private Integer userId;

    @SerializedName("Email")

    private String email;
    @SerializedName("Mobile")

    private String mobile;
    @SerializedName("RoleId")
    private Integer roleId;
    @SerializedName("IsVerified")

    private Boolean isVerified;
    @SerializedName("UserDetails")

    private List<UserDetail> userDetails = new ArrayList<>();
    @SerializedName("IsActive")

    private Boolean isActive;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", roleId=" + roleId +
                ", isVerified=" + isVerified +
                ", userDetails=" + userDetails.get(0).toString() +
                ", isActive=" + isActive +
                '}';
    }
}



