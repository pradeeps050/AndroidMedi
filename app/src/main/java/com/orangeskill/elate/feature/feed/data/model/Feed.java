package com.orangeskill.elate.feature.feed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("LogoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("AuthorName")
    @Expose
    private String authorName;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
        return "Feed{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", authorName='" + authorName + '\'' +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
