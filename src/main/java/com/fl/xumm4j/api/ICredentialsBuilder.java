package com.fl.xumm4j.api;

import com.fl.xumm4j.sdk.builder.CredentialsBuilder;

public interface ICredentialsBuilder {
    String ERROR_APIKEY = "Empty API Key";
    String ERROR_SECRET_KEY = "Empty Secret Key";

    /**
     * @param apiKey
     * @return
     */
    CredentialsBuilder.builder apiKey(String apiKey);

    /**
     * @param secretKey
     * @return
     */
    CredentialsBuilder.builder secretKey(String secretKey);

    /**
     * @return
     */
    CredentialsBuilder build();
}
