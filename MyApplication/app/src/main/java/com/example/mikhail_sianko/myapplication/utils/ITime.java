package com.example.mikhail_sianko.myapplication.utils;

public interface ITime {
    long getCurrentTimeInMillis();

    String getFriendlyFormattedTime();

    String getDayPart();

    String getTimeFormat();
}
