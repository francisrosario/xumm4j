package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.builder.Credentials;

public interface AppCredentials {
    String EMPTY_APIKEY = "Empty API Key";
    String EMPTY_SECRETKEY = "Empty API Key";

    Credentials.builder apiKey(String apiKey);
    Credentials.builder secretKey(String secretKey);
    void validate();
    Credentials build();
}
