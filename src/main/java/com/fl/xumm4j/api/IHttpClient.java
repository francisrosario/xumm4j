package com.fl.xumm4j.api;

import okhttp3.Response;

public interface IHttpClient {

    /**
     * @param url
     * @return
     */
    Response doGet(String url);

    /**
     * @param payload
     * @return
     */
    Response doPost(String payload);
}
