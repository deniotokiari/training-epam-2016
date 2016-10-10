package com.example.mikhail_sianko.myapplication.examples.utils;

import java.text.SimpleDateFormat;

public class CustomSimpleDayFormat {
    public String getFormattedTime (String pattern, long time) {
        return new SimpleDateFormat(pattern).format(time);
    }
}
