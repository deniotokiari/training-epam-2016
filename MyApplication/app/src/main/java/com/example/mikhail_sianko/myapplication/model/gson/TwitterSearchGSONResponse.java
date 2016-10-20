package com.example.mikhail_sianko.myapplication.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterSearchGSONResponse {

    @SerializedName("statuses")
    private List<TwitterSearchStatuses> mStatuses;

    public List<TwitterSearchStatuses> getStatuses() {
        return mStatuses;
    }
}
