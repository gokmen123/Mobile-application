package com.example.midtermproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Blog implements Parcelable {

    private User writer;
    private int like, dislike;
    private final int uniqueID;
    private String date, text, imgURL;

    public Blog( int like, int dislike, int uniqueID, String date, String text, String imgURL) {

        this.like = like;
        this.dislike = dislike;
        this.uniqueID = uniqueID;
        this.date = date;
        this.text = text;
        this.imgURL = imgURL;
    }





    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
