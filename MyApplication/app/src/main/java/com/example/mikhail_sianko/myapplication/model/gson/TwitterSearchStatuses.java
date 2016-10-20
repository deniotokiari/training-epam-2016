package com.example.mikhail_sianko.myapplication.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TwitterSearchStatuses {

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private TwitterUser twitterUser;

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public TwitterUser getTwitterUser() {
        return twitterUser;
    }
}
