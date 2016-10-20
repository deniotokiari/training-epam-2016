package com.example.mikhail_sianko.myapplication.http;

import android.support.annotation.Nullable;

public class HttpClientFactory {

    public static enum Type {

        HTTP_PURE, OK_HTTP;

    }

    @Nullable
    public static IHttpClient get(final Type pType) {
        if (pType == Type.HTTP_PURE) {
            return new HttpClientAbstraction();
        } else if (pType == Type.OK_HTTP) {
            return new OkHttpClient();
        } else {
            return null;
        }
    }

    public static IHttpClient getDefault() {
        return get(Type.HTTP_PURE);
    }

}
