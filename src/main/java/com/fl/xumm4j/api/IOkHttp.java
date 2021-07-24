package com.fl.xumm4j.api;

import okhttp3.Response;

public interface IOkHttp {

    Response doGet(String url);
    Response doPost(String payload);
}
