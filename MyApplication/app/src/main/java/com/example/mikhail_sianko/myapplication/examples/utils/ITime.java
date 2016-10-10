package com.example.mikhail_sianko.myapplication.examples.utils;

public interface ITime {
    long getCurrentTimeInMillis();

    String getFriendlyFormattedTime();

    String getDayPart();

    String getTimeFormat();
}
