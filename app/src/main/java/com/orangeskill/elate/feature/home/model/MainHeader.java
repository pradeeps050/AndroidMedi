package com.orangeskill.elate.feature.home.model;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.framework.application.AppInstance;

public class MainHeader implements Parcelable {
    private String imageUrl;
    private String name;
    private String subCategory;
    private String note;
    private String curatedBy;
    private int id;

    public MainHeader(Parcel in) {
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.subCategory = in.readString();
        this.note = in.readString();
        this.curatedBy = in.readString();
        this.id = in.readInt();
    }

    public MainHeader(String imageUrl, String name, String subCategory, String note, String curatedBy, int id) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.subCategory = subCategory;
        this.note = note;
        this.curatedBy = curatedBy;
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.name);
        parcel.writeString(this.subCategory);
        parcel.writeString(this.note);
        parcel.writeString(this.curatedBy);
        parcel.writeInt(this.id);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public MainHeader createFromParcel(Parcel parcel) {
            return new MainHeader(parcel);
        }

        @Override
        public MainHeader[] newArray(int i) {
            return new MainHeader[i];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCuratedBy() {
        return curatedBy;
    }

    public void setCuratedBy(String curatedBy) {
        this.curatedBy = curatedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @BindingAdapter({"bind:mainImage"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(AppInstance.getInstance().getContext()).load(imageUrl).into(imageView);
    }


}
