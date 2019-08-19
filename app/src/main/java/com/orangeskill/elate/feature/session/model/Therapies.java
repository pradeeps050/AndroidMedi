package com.orangeskill.elate.feature.session.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Therapies {

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("TherapyCategoryId")
    @Expose
    private Integer therapyCategoryId;

    @SerializedName("SubCatagoryName")
    @Expose
    private String subCatagoryName;

    @SerializedName("Tags")
    @Expose
    private String tags;

    @SerializedName("DispalyOrder")
    @Expose
    private Integer dispalyOrder;

    @SerializedName("IconUrl")
    @Expose
    private String iconPath;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTherapyCategoryId() {
        return therapyCategoryId;
    }

    public void setTherapyCategoryId(Integer therapyCategoryId) {
        this.therapyCategoryId = therapyCategoryId;
    }

    public String getSubCatagoryName() {
        return subCatagoryName;
    }

    public void setSubCatagoryName(String subCatagoryName) {
        this.subCatagoryName = subCatagoryName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getDispalyOrder() {
        return dispalyOrder;
    }

    public void setDispalyOrder(Integer dispalyOrder) {
        this.dispalyOrder = dispalyOrder;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Therapies{" +
                "id=" + id +
                ", therapyCategoryId=" + therapyCategoryId +
                ", subCatagoryName='" + subCatagoryName + '\'' +
                ", tags='" + tags + '\'' +
                ", dispalyOrder=" + dispalyOrder +
                ", iconPath='" + iconPath + '\'' +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
