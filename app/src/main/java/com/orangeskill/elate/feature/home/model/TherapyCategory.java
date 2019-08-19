package com.orangeskill.elate.feature.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TherapyCategory {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ThumbnailPath")
    @Expose
    private String imageUrl;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsActive")
    @Expose
    private boolean isActive;
    @SerializedName("CreatedBy")
    @Expose
    private int createdBy;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "TherapyCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
