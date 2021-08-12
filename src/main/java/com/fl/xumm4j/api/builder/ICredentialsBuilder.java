package com.fl.xumm4j.api.builder;

import com.fl.xumm4j.sdk.builder.CredentialsBuilder;

/**
 * The interface Credentials builder.
 */
public interface ICredentialsBuilder {
    /**
     * The constant ERROR_APIKEY.
     */
    String ERROR_APIKEY = "Empty API Key";
    /**
     * The constant ERROR_SECRET_KEY.
     */
    String ERROR_SECRET_KEY = "Empty Secret Key";

    /**
     * Api key credentials builder . builder.
     *
     * @param apiKey the API Key obtained from <a href="https://apps.xumm.dev/">https://apps.xumm.dev/</a>
     * @return the credentials builder . builder
     */
    CredentialsBuilder.builder apiKey(String apiKey);

    /**
     * Secret key credentials builder . builder.
     *
     * @param secretKey the API Secret obtained from <a href="https://apps.xumm.dev/">https://apps.xumm.dev/</a>
     * @return the credentials builder . builder
     */
    CredentialsBuilder.builder secretKey(String secretKey);

    /**
     * Build credentials builder.
     *
     * @return the credentials builder
     */
    CredentialsBuilder build();
}
