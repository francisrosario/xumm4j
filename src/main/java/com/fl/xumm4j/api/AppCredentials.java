package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.builder.CredentialsBuilder;

public interface AppCredentials {
    String ERROR_APIKEY = "Empty API Key";
    String ERROR_SECRETKEY = "Empty Secret Key";

    CredentialsBuilder.builder apiKey(String apiKey);
    CredentialsBuilder.builder secretKey(String secretKey);
    CredentialsBuilder build();
}
