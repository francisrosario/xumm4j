package com.fl.xumm4j.api;

import okhttp3.Response;

public interface OkHttp {

    Response doGet(String url);
    Response doPost(String payload);
}
