package com.orangeskill.elate.feature.playlist.ui.therapy.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Program {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ProgramTypeId")
    @Expose
    private Integer programTypeId;
    @SerializedName("TherapyId")
    @Expose
    private Integer therapyId;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
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

    public Integer getProgramTypeId() {
        return programTypeId;
    }

    public void setProgramTypeId(Integer programTypeId) {
        this.programTypeId = programTypeId;
    }

    public Integer getTherapyId() {
        return therapyId;
    }

    public void setTherapyId(Integer therapyId) {
        this.therapyId = therapyId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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
        return "Program{" +
                "id=" + id +
                ", programTypeId=" + programTypeId +
                ", therapyId=" + therapyId +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", displayOrder=" + displayOrder +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
