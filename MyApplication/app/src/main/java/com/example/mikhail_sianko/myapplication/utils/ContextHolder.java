package com.example.mikhail_sianko.myapplication.utils;

import android.content.Context;

public enum ContextHolder {

    INSTANCE;

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public void setContext(final Context pContext) {
        mContext = pContext;
    }

    public static Context get() {
        return INSTANCE.mContext;
    }

    public static void set(final Context pContext) {
        INSTANCE.mContext = pContext;
    }

}
