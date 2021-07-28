package com.fl.xumm4j.api;

import okhttp3.Response;

public interface IHttpClient {

    Response doGet(String url);
    Response doPost(String url, String data);

    Response doDelete(String url);
}
