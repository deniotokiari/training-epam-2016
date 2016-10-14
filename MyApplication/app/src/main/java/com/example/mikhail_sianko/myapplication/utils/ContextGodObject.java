package com.example.mikhail_sianko.myapplication.utils;

import android.content.Context;

public final class ContextGodObject {

    private static ContextGodObject sContextGodObject;

    private Context mContext;

    private ContextGodObject() {

    }

    public static ContextGodObject getInstance() {
        if (sContextGodObject == null) {
            sContextGodObject = new ContextGodObject();
        }

        return sContextGodObject;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(final Context pContext) {
        mContext = pContext;
    }

}
