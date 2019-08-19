package com.orangeskill.elate.framework.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class WinBotEntity {

    @Expose
    @PrimaryKey
    @SerializedName("MessageID")
    int messageID;
    @Expose
    @SerializedName("Date")
    String date;
    @Expose
    @SerializedName("Message")
    String message;

    String dateToShow;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateToShow() {
        return dateToShow;
    }

    public void setDateToShow(String dateToShow) {
        this.dateToShow = dateToShow;
    }
}
