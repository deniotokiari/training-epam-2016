package com.example.mikhail_sianko.myapplication.utils;

import java.util.Calendar;

public class Time implements ITime {

    private CustomSimpleDayFormat mCustomSimpleDayFormat;

    public Time(CustomSimpleDayFormat pCustomSimpleDayFormat) {
        setCustomSimpleDayFormat(pCustomSimpleDayFormat);
    }

    @Override
    public long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public String getFriendlyFormattedTime() {
        String timeFormat = getTimeFormat();
        if (timeFormat.contains("HH") && timeFormat.contains("mm")) {
            return mCustomSimpleDayFormat.getFormattedTime("HH:mm:ss", getCurrentTimeInMillis());
        }

        throw new IllegalArgumentException("time format not valid");

    }

    public void setCustomSimpleDayFormat(CustomSimpleDayFormat mCustomSimpleDayFormat) {
        this.mCustomSimpleDayFormat = mCustomSimpleDayFormat;
    }

    public String getTimeFormat() {
        return "HH:mm:ss";
    }

    @Override
    public String getDayPart() {
        Calendar calendar = getCalendar();
        calendar.setTimeInMillis(getCurrentTimeInMillis());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (hours > 0 && hours < 7) {
            return "night";
        } else if (hours >= 7 && hours <= 12) {
            return "morning";
        } else if (hours > 12 && hours < 15) {
            return "lunch";
        } else if (hours >= 15 && hours < 22) {
            return "evening";
        } else {
            return "night";
        }
    }

    private Calendar getCalendar() {
        return Calendar.getInstance();
    }
}
