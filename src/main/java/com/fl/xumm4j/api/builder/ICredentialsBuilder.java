package com.fl.xumm4j.api.builder;

import com.fl.xumm4j.sdk.builder.CredentialsBuilder;

/**
 * The interface Credentials builder.
 */
public interface ICredentialsBuilder {
    /**
     * The constant ERROR_APIKEY contains the error message whenever {@link CredentialsBuilder.builder#validate() is invoked and API Key is missing.}
     */
    String ERROR_APIKEY = "Empty API Key";
    /**
     * The constant ERROR_SECRET_KEY contains the error message whenever {@link CredentialsBuilder.builder#validate() is invoked and API Secret is missing.}
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
     * Validate checks the API Key and Secret key if either one of them is missing from the builder attribute.
     */
    void validate();

    /**
     * Build credentials builder.
     *
     * @return the credentials builder
     */
    CredentialsBuilder build();
}
