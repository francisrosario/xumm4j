package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.builder.Credentials;

public interface AppCredentials {
    String ERROR_APIKEY = "Empty API Key";
    String ERROR_SECRETKEY = "Empty Secret Key";

    Credentials.builder apiKey(String apiKey);
    Credentials.builder secretKey(String secretKey);
    Credentials build();
}
