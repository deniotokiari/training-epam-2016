package com.example.mikhail_sianko.myapplication.http;

import java.io.InputStream;

public interface IHttpClient {

    InputStream get(IRequest pRequest);

    InputStream post(IRequest pRequest);

    interface IRequest {

    }

}
