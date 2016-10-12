package com.example.mikhail_sianko.myapplication.api;

import com.epam.training.LoginHelper;
import com.example.mikhail_sianko.myapplication.BuildConfig;

public class API {

    public static String getUser() {
        return LoginHelper.getLogin() + LoginHelper.getPassword();
    }

    public static String getBaseUrl() {
        return BuildConfig.BACKEND_URL;
    }
}
