package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.Credentials;

public interface AppCredentials {
    String EMPTY_APIKEY = "Empty API Key";
    String EMPTY_SECRETKEY = "Empty API Key";

    Credentials.Builder apiKey(String apiKey);
    Credentials.Builder secretKey(String secretKey);
    void validate();
    Credentials build();
}
