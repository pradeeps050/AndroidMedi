package com.orangeskill.elate.feature.session.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TherapySession {
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("ThumbnailPath")
    @Expose
    private String thumbnailPath;

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("CuratedByText")
    @Expose
    private String curatedBy;

    @SerializedName("CuretedByImage")
    @Expose
    private String curatedByImage;

    @SerializedName("Therapies")
    @Expose
    private List<Therapies> therapies;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    @SerializedName("OverView")
    @Expose
    private String overview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuratedBy() {
        return curatedBy;
    }

    public void setCuratedBy(String curatedBy) {
        this.curatedBy = curatedBy;
    }

    public List<Therapies> getTherapies() {
        return therapies;
    }

    public void setTherapies(List<Therapies> therapies) {
        this.therapies = therapies;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
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
        return "TherapySession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", description='" + description + '\'' +
                ", curatedBy='" + curatedBy + '\'' +
                ", curatedByImage='" + curatedByImage + '\'' +
                ", therapies=" + therapies +
                ", isActive=" + isActive +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

    public String getCuratedByImage() {
        return curatedByImage;
    }

    public void setCuratedByImage(String curatedByImage) {
        this.curatedByImage = curatedByImage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
