package com.fl.xumm4j.api;

import com.fl.xumm4j.sdk.builder.CredentialsBuilder;

public interface ICredentialsBuilder {
    String ERROR_APIKEY = "Empty API Key";
    String ERROR_SECRET_KEY = "Empty Secret Key";

    CredentialsBuilder.builder apiKey(String apiKey);
    CredentialsBuilder.builder secretKey(String secretKey);
    CredentialsBuilder build();
}
