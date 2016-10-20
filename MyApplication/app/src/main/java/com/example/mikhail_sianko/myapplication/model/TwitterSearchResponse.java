package com.example.mikhail_sianko.myapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TwitterSearchResponse {

    private List<TwitterSearchStatus> mTwitterSearchStatuses = new ArrayList<>();

    public TwitterSearchResponse(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        if (jsonObject.has("statuses")) {
            JSONArray statusesJsonArray = jsonObject.getJSONArray("statuses");
            for (int i = 0; i < statusesJsonArray.length(); i++) {
                JSONObject statusObject = statusesJsonArray.getJSONObject(i);

                TwitterSearchStatus twitterSearchStatus = new TwitterSearchStatus(statusObject);
                mTwitterSearchStatuses.add(twitterSearchStatus);

            }

        }
    }

    public List<TwitterSearchStatus> getTwitterSearchStatuses() {
        return mTwitterSearchStatuses;
    }
}
