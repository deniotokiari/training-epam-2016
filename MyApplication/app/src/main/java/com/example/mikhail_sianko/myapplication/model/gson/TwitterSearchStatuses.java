package com.example.mikhail_sianko.myapplication.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitterSearchStatuses {

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private TwitterUser twitterUser;

    public boolean changeMe;

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public TwitterUser getTwitterUser() {
        return twitterUser;
    }

    public static TwitterSearchStatuses dummy(String user, String text) {
        TwitterSearchStatuses statuses = new TwitterSearchStatuses();
        statuses.text = text;
        statuses.twitterUser = new TwitterUser();
        statuses.twitterUser.setName(user);
        return statuses;
    }

    public static List<TwitterSearchStatuses> listDummy() {
        List<TwitterSearchStatuses> results = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            results.add(TwitterSearchStatuses.dummy("Text " + i, "User " + i));
        }
        return results;
    }
}
