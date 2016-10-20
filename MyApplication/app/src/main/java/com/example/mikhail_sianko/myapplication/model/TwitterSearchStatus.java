package com.example.mikhail_sianko.myapplication.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TwitterSearchStatus {

    public TwitterSearchStatus(JSONObject statusJsonObject) throws JSONException {
        mCreatedAt = statusJsonObject.getString("created_at");
        mText = statusJsonObject.getString("text");

        JSONObject userObject = statusJsonObject.getJSONObject("user");
        if (userObject != null) {
            mUserName = userObject.getString("name");
        }
    }

    private String mCreatedAt;

    private String mText;

    private String mUserName;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getText() {
        return mText;
    }

    public String getUserName() {
        return mUserName;
    }
}
